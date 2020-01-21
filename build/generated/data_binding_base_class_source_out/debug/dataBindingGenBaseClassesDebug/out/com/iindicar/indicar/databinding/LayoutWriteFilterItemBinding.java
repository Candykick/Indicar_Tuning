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

public abstract class LayoutWriteFilterItemBinding extends ViewDataBinding {
  @Bindable
  protected String mTitle;

  protected LayoutWriteFilterItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setTitle(@Nullable String title);

  @Nullable
  public String getTitle() {
    return mTitle;
  }

  @NonNull
  public static LayoutWriteFilterItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutWriteFilterItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutWriteFilterItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_write_filter_item, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutWriteFilterItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutWriteFilterItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutWriteFilterItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_write_filter_item, null, false, component);
  }

  public static LayoutWriteFilterItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static LayoutWriteFilterItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (LayoutWriteFilterItemBinding)bind(component, view, com.iindicar.indicar.R.layout.layout_write_filter_item);
  }
}
