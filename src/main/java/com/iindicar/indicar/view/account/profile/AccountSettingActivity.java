package com.iindicar.indicar.view.account.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.source.user.UserDataSource;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.ActivityAccountSettingBinding;
import com.iindicar.indicar.databinding.HolderAccountSettingItemBinding;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.ByteLengthUtil;
import com.iindicar.indicar.utils.CustomDialog;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;
import com.iindicar.indicar.view.listener.OnItemClickListener;
import com.iindicar.indicar.view.main.login.LoginActivity;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.linecorp.linesdk.api.LineApiClient;
import com.linecorp.linesdk.api.LineApiClientBuilder;

import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AccountSettingActivity extends BaseActivity<ActivityAccountSettingBinding> implements OnItemClickListener {

    public static final String RESULT_USER_NAME_CHANGED = "RESULT_USER_NAME_CHANGED";

    public final int MAX_BYTE_OF_USER_NAME = 20; // 유저이름(닉네임) 최대 입력 수

    public final ObservableField<String> originalName = new ObservableField<>();
    public final ObservableField<String> inputName = new ObservableField<>();

    private AccountAdapter adapter;

    private static LineApiClient lineApiClient;
    private User loginUser;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginUser = UserRepository.getInstance().getLoginUser();

        originalName.set(loginUser.getUserName());
        inputName.set(loginUser.getUserName());

        binding.setActivity(this);

        LineApiClientBuilder apiClientBuilder = new LineApiClientBuilder(this, getString(R.string.line_channel_id));
        lineApiClient = apiClientBuilder.build();

        initView();
    }

    private void initView() {

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());
        binding.toolbar.tvCenter.setText(getString(R.string.account_setting));

        // 리사이클러뷰 생성
        adapter = new AccountAdapter(this);
        adapter.setOnItemClickListener(this);

        binding.recyclerAccount.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerAccount.setAdapter(adapter);
/*
        // 완료 버튼 리스너
        binding.btnDone.setOnClickListener(v -> onDoneButtonClick());

        // 글자 수 20byte 제한하는 필터를 EditText 에 적용시킨다
        InputFilter[] filters = new InputFilter[] {new ByteLengthUtil(MAX_BYTE_OF_USER_NAME, "KSC5601")};
        binding.etName.setFilters(filters);
        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputName.set(editable.toString());
            }
        });*/
    }

    public void onDoneButtonClick(){

        binding.loading.setVisibility(View.VISIBLE);

        changeUserName();
    }

    @SuppressLint("CheckResult")
    public void changeUserName(){

        UserRepository.getInstance().updateData(RequestMapper.userUpdateMapping(inputName.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {

                    // 수정된 정보 sharedPreference 저장
                    SharedPreferences prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putString("name", inputName.get());
                    editor.putLong("profileEditDate", System.currentTimeMillis());
                    editor.apply();

                    Intent result = new Intent();
                    result.putExtra(RESULT_USER_NAME_CHANGED, true);
                    setResult(RESULT_OK, result);
                    finish();
                }, error -> {});
    }

    /**
     * item 클릭 콜백 */
    @Override
    public void onItemClick(int position) {

        switch (position){
            case 0: // 로그아웃
                logout(getString(R.string.strLogoutSuccess));
                break;
            case 1: // 회원탈퇴
                CustomDialog dialog = new CustomDialog(this, R.layout.dialog_remove_account,
                        () -> signout());
                dialog.setCancelable(true);
                dialog.show();
                break;
        }
    }

    /**
     * 회원 탈퇴 */
    private void signout() {

        // 회원 탈퇴 요청
        UserRepository.getInstance().removeAuth(loginUser.getUserId(), new UserDataSource.LoadDataCallback<LoadDataResponse>() {
            @Override
            public void onDataLoaded(String result, LoadDataResponse item) {
                UserRepository.getInstance().removeUser(loginUser.getUserId(), new UserDataSource.LoadDataCallback<LoadDataResponse>() {
                    //중요: 실제 정보는 String result만 가져옴. item = null.
                    @Override
                    public void onDataLoaded(String result, LoadDataResponse item) {
                        if(result.equals("S")) {
                            logout(getString(R.string.strSignoutSuccess));
                        } else {
                            logout(getString(R.string.strSignoutErr));
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        logout(getString(R.string.strSignoutErr));
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                logout(getString(R.string.strSignoutErr));
            }
        });
    }

    /**
     * 로그아웃 */
    private void logout(String toastMessage) {
        cancelLogin();
        Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityCompat.finishAffinity(this);
        startActivity(intent);
    }

    //유저 식별 실패 시 로그아웃 메서드
    private void cancelLogin() {
        SharedPreferences prefLogin = getApplicationContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        } else if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
        } else if (!Session.getCurrentSession().isClosed()) {
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {

                }
            });
        } else if(lineApiClient.getCurrentAccessToken().isSuccess()) {
            new LineLogout().execute();
        }

        SharedPreferences.Editor editor = prefLogin.edit();
        editor.putLong("profileEditDate", 0);
        editor.putString("id", "0");
        editor.putString("login_method", "0");
        editor.putString("name", "0");
        editor.putString("profile_img_url", "0");
        editor.putString("email", "fail");
        editor.putString("apiid","0");
        editor.putBoolean("EventAlarm", true);
        editor.putBoolean("OtherAlarm", true);
        Locale systemLocale = getResources().getConfiguration().locale;
        editor.putString("locale", systemLocale.getLanguage());
        editor.apply();
    }

    // 라인 로그아웃 메서드
    private class LineLogout extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            lineApiClient.logout();
            return null;
        }
    }

    public class AccountSettingItem {

        private String option;

        public AccountSettingItem(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }
    }

    public class AccountAdapter extends BaseRecyclerAdapter<AccountSettingItem, AccountAdapter.ViewHolder>{

        public AccountAdapter(Context context) {
            super(context);
            this.addItem(new AccountSettingItem(context.getString(R.string.sign_out)));
            this.addItem(new AccountSettingItem(context.getString(R.string.remove_account)));
        }

        @Override
        protected void onBindView(ViewHolder holder, int position) {
            holder.binding.setItem(itemList.get(position));
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_account_setting_item, parent, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            protected HolderAccountSettingItemBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
