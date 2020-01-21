package com.iindicar.indicar.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.iindicar.indicar.utils.LocaleHelper;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    protected B binding;
    protected Resources resources;

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 화면 세로 고정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // 상태바 글씨 검정
        }
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        resources = getResources();
    }

    public void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

}