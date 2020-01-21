package com.iindicar.indicar.view.tuning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.iindicar.indicar.R;
import com.iindicar.indicar.UnityPlayerActivity;
import com.iindicar.indicar.data.source.car.CarRepository;
import com.iindicar.indicar.databinding.ActivityTuningBinding;
import com.iindicar.indicar.model.Car;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.CustomDialog;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.utils.KeyboardUtil;
import com.iindicar.indicar.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TuningActivity extends BaseActivity<ActivityTuningBinding> implements TuningContract.View{

    // 차량 로고 관련
    private static final int NUM_OF_CAR_LOGO = 8;
    private static final int[] CAR_LOGO_IMAGE_LIST = {
            R.drawable.tuninglogo0,
            R.drawable.tuninglogo1,
            R.drawable.tuninglogo2,
            R.drawable.tuninglogo3,
            R.drawable.tuninglogo4,
            R.drawable.tuninglogo5,
            R.drawable.tuninglogo6,
            R.drawable.tuninglogo7,
    };

    private TuningPresenter presenter;
    private TuningAdapter adapter;

    int logoIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tuning;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TuningPresenter(this, CarRepository.getInstance());

        adapter = new TuningAdapter(this);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);

        initView();

        presenter.loadItems();
    }

    private void initView() {

        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_tuning);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_back);
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());

        binding.recyclerCarList.setAdapter(adapter);
        binding.recyclerCarList.setEmptyView(binding.emptyView);
        binding.recyclerCarList.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerCarList.setItemViewCacheSize(30);
        binding.recyclerCarList.setNestedScrollingEnabled(false);

        // 로고 왼쪽 버튼 이벤트
        binding.btnTLeft.setOnClickListener(v -> {
            if (logoIndex > 0) {
                changeTab(--logoIndex);
                presenter.getSelectedCarList(logoIndex);
            } else {
                logoIndex = NUM_OF_CAR_LOGO - 1;
                changeTab(logoIndex);
                presenter.getSelectedCarList(logoIndex);
            }
        });

        //로고 오른쪽 버튼 이벤트
        binding.btnTRight.setOnClickListener(v -> {
            if (logoIndex < (NUM_OF_CAR_LOGO - 1)) {
                changeTab(++logoIndex);
                presenter.getSelectedCarList(logoIndex);
            } else {
                logoIndex = 0;
                changeTab(logoIndex);
                presenter.getSelectedCarList(logoIndex);
            }
        });

        // 검색 완료 버튼 이벤트
        binding.btnSubmit.setOnClickListener(v -> {
            // 어댑터에 전달
            presenter.onSearchCar(binding.etSearch.getText().toString());
            // 키보드 닫기
            KeyboardUtil.closeKeyboard(this, binding.etSearch);
        });

        binding.etSearch.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onSearchCar(editable.toString());
            }
        });
    }

    @Override
    public void changeTab(int index){
        binding.etSearch.setText("");
        GlideUtil.loadImageResource(binding.tabLogo, CAR_LOGO_IMAGE_LIST[logoIndex]);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public void startUnityActivity(final Car item) {

        CustomDialog dialog = new CustomDialog(this, R.layout.dialog_unity_download,
                () -> TedPermission.with(this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {

                                SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

                                User user = new User();
                                user.setUserId(prefLogin.getString("id", null));
                                user.setUserEmail(prefLogin.getString("email", null));
                                user.setUserName(prefLogin.getString("name", null));
                                user.setUserLoginMethod(prefLogin.getString("login_method", null));
                                user.setUserImageUrl(prefLogin.getString("profile_img_url", null));

                                Intent intent = new Intent(getApplicationContext(), UnityPlayerActivity.class);
                                intent.putExtra("login_method", user.getUserLoginMethod());
                                intent.putExtra("email", user.getUserEmail());
                                intent.putExtra("scene","new_scene");
                                intent.putExtra("car_name", item.getCarName());

                                startActivity(intent);
                            }
                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                makeToast(R.string.strWhatPermissionDeny + deniedPermissions.toString());
                            }
                        })
                        .setDeniedMessage(getString(R.string.strPermissionDenied))
                        .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA)
                        .check());

        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onCarDataListLoaded(List<Car> list) {
        adapter.setItemAllList(list);
        adapter.addItems(list);
        presenter.getSelectedCarList(0);
    }
}
