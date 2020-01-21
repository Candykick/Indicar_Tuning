package com.iindicar.indicar.view.account.profile;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityLanguageSettingBinding;
import com.iindicar.indicar.databinding.HolderLanguageSettingItemBinding;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;
import com.iindicar.indicar.view.listener.OnItemClickListener;
import com.iindicar.indicar.view.main.login.LoginActivity;
import com.iindicar.indicar.view.main.splash.SplashActivity;

public class LanguageSettingActivity extends BaseActivity<ActivityLanguageSettingBinding> implements OnItemClickListener {

    public static final String RESULT_LANGUAGE_CHANGED = "RESULT_LANGUAGE_CHANGED";

    public final ObservableField<String> originalLanguage = new ObservableField<>(); // 변경 전 언어 기억
    public final ObservableField<String> language = new ObservableField<>(); // 변경 후

    private LanguageAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_language_setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 현재 언어 정보 받아오기
        originalLanguage.set(LocaleHelper.getLanguage(getApplicationContext()));
        language.set(originalLanguage.get());

        initView();
    }

    private void initView() {

        binding.setActivity(this);

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());
        binding.toolbar.tvCenter.setText(getString(R.string.language_setting));

        // 리사이클러뷰 생성
        adapter = new LanguageAdapter(this);
        adapter.setOnItemClickListener(this);

        binding.recyclerLanguage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerLanguage.setAdapter(adapter);

        // 완료 버튼 리스너
        binding.btnDone.setOnClickListener(v -> onDoneButtonClick());
    }

    /**
     * 언어 변경 완료 버튼 콜백
     * 이 함수는 변경사항이 있을 경우에만 호출 가능 (버튼 Enabled) */
    private void onDoneButtonClick() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(getString(R.string.strWannaChangeLang));

        dialog.setPositiveButton(R.string.strRestart, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                changeLanguage(language.get());

//                Context context = getApplicationContext();
//                PackageManager packageManager = context.getPackageManager();
//                Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
//                ComponentName componentName = intent.getComponent();
//                Intent mainIntent = Intent.makeRestartActivityTask(componentName);
//                context.startActivity(mainIntent);
//                System.exit(0);
            }
        });

        dialog.setNegativeButton(R.string.strReSCancel, (dialog1, which) -> {

        });

        AlertDialog alert = dialog.create();
        alert.setIcon(R.drawable.app_icon);
        alert.setTitle(R.string.language_setting);
        alert.show();
    }

    /**
     * 어플리케이션 언어 변경 */
    private void changeLanguage(String lang){
        LocaleHelper.setLocale(getApplicationContext(), lang);

        // 재시작
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityCompat.finishAffinity(this);
        startActivity(intent);
    }

    /**
     * 언어 item 클릭 콜백 */
    @Override
    public void onItemClick(int position) {

        language.set(adapter.getItem(position).getLanguage());
    }

    public class LanguageSettingItem {

        private String language;

        private String languageString;

        public LanguageSettingItem(String language, String languageString) {
            this.language = language;
            this.languageString = languageString;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLanguageString() {
            return languageString;
        }

        public void setLanguageString(String languageString) {
            this.languageString = languageString;
        }
    }

    public class LanguageAdapter extends BaseRecyclerAdapter<LanguageSettingItem, LanguageAdapter.ViewHolder>{

        public LanguageAdapter(Context context) {
            super(context);
            this.addItem(new LanguageSettingItem("ko", getString(R.string.korean)));
            this.addItem(new LanguageSettingItem("en", getString(R.string.english)));
        }

        @Override
        protected void onBindView(ViewHolder holder, int position) {
            holder.binding.setItem(itemList.get(position));
            holder.binding.setLanguage(language);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_language_setting_item, parent, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            protected HolderLanguageSettingItemBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
