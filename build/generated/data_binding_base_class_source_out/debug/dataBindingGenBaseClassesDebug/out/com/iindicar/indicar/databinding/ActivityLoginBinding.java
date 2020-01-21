package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.iindicar.indicar.view.main.login.LoginActivity;

public abstract class ActivityLoginBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout btnLoginFacebook;

  @NonNull
  public final LinearLayout btnLoginGoogle;

  @NonNull
  public final LinearLayout btnLoginKakao;

  @NonNull
  public final LinearLayout btnLoginLine;

  @NonNull
  public final LinearLayout loadinglogin;

  @NonNull
  public final ViewPager viewPagerWelcome;

  @Bindable
  protected LoginActivity mActivity;

  protected ActivityLoginBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout btnLoginFacebook, LinearLayout btnLoginGoogle,
      LinearLayout btnLoginKakao, LinearLayout btnLoginLine, LinearLayout loadinglogin,
      ViewPager viewPagerWelcome) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnLoginFacebook = btnLoginFacebook;
    this.btnLoginGoogle = btnLoginGoogle;
    this.btnLoginKakao = btnLoginKakao;
    this.btnLoginLine = btnLoginLine;
    this.loadinglogin = loadinglogin;
    this.viewPagerWelcome = viewPagerWelcome;
  }

  public abstract void setActivity(@Nullable LoginActivity activity);

  @Nullable
  public LoginActivity getActivity() {
    return mActivity;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLoginBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_login, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLoginBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_login, null, false, component);
  }

  public static ActivityLoginBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityLoginBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityLoginBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_login);
  }
}
