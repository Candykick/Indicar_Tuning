package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ActivityNotifySettingBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView recyclerNotification;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  protected ActivityNotifySettingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView recyclerNotification, LayoutTextToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerNotification = recyclerNotification;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  @NonNull
  public static ActivityNotifySettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNotifySettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNotifySettingBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_notify_setting, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityNotifySettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNotifySettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNotifySettingBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_notify_setting, null, false, component);
  }

  public static ActivityNotifySettingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityNotifySettingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityNotifySettingBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_notify_setting);
  }
}
