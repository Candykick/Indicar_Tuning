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
import android.widget.ImageButton;
import com.iindicar.indicar.view.account.profile.NotifySettingActivity;

public abstract class HolderNotifySettingItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnAlarm;

  @Bindable
  protected NotifySettingActivity.NotifySettingItem mItem;

  protected HolderNotifySettingItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnAlarm) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnAlarm = btnAlarm;
  }

  public abstract void setItem(@Nullable NotifySettingActivity.NotifySettingItem item);

  @Nullable
  public NotifySettingActivity.NotifySettingItem getItem() {
    return mItem;
  }

  @NonNull
  public static HolderNotifySettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderNotifySettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderNotifySettingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_notify_setting_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderNotifySettingItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderNotifySettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderNotifySettingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_notify_setting_item, null, false, component);
  }

  public static HolderNotifySettingItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderNotifySettingItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderNotifySettingItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_notify_setting_item);
  }
}
