package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iindicar.indicar.view.account.profile.AccountSettingActivity;

public abstract class HolderAccountSettingItemBinding extends ViewDataBinding {
  @Bindable
  protected AccountSettingActivity.AccountSettingItem mItem;

  protected HolderAccountSettingItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setItem(@Nullable AccountSettingActivity.AccountSettingItem item);

  @Nullable
  public AccountSettingActivity.AccountSettingItem getItem() {
    return mItem;
  }

  @NonNull
  public static HolderAccountSettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderAccountSettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderAccountSettingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_account_setting_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderAccountSettingItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderAccountSettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderAccountSettingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_account_setting_item, null, false, component);
  }

  public static HolderAccountSettingItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderAccountSettingItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderAccountSettingItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_account_setting_item);
  }
}
