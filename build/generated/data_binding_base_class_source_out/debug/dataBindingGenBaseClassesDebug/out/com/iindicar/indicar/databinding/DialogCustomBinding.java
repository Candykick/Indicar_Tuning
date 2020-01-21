package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public abstract class DialogCustomBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout btnCancel;

  @NonNull
  public final LinearLayout btnSubmit;

  @NonNull
  public final FrameLayout frame;

  protected DialogCustomBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout btnCancel, LinearLayout btnSubmit, FrameLayout frame) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnCancel = btnCancel;
    this.btnSubmit = btnSubmit;
    this.frame = frame;
  }

  @NonNull
  public static DialogCustomBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static DialogCustomBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<DialogCustomBinding>inflate(inflater, com.iindicar.indicar.R.layout.dialog_custom, root, attachToRoot, component);
  }

  @NonNull
  public static DialogCustomBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static DialogCustomBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<DialogCustomBinding>inflate(inflater, com.iindicar.indicar.R.layout.dialog_custom, null, false, component);
  }

  public static DialogCustomBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static DialogCustomBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (DialogCustomBinding)bind(component, view, com.iindicar.indicar.R.layout.dialog_custom);
  }
}
