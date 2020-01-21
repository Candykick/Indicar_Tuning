package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.iindicar.indicar.view.account.profile.AccountSettingActivity;

public abstract class ActivityAccountSettingBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout loading;

  @NonNull
  public final RecyclerView recyclerAccount;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  @Bindable
  protected AccountSettingActivity mActivity;

  protected ActivityAccountSettingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout loading, RecyclerView recyclerAccount,
      LayoutTextToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.loading = loading;
    this.recyclerAccount = recyclerAccount;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  public abstract void setActivity(@Nullable AccountSettingActivity activity);

  @Nullable
  public AccountSettingActivity getActivity() {
    return mActivity;
  }

  @NonNull
  public static ActivityAccountSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityAccountSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityAccountSettingBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_account_setting, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAccountSettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityAccountSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityAccountSettingBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_account_setting, null, false, component);
  }

  public static ActivityAccountSettingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityAccountSettingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityAccountSettingBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_account_setting);
  }
}
