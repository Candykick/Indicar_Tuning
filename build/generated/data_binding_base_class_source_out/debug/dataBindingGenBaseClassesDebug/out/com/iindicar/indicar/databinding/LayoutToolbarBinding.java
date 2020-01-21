package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public abstract class LayoutToolbarBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnLeft;

  @NonNull
  public final ImageButton btnRight;

  @NonNull
  public final RelativeLayout container;

  @NonNull
  public final ImageView ivCenter;

  protected LayoutToolbarBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnLeft, ImageButton btnRight, RelativeLayout container,
      ImageView ivCenter) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnLeft = btnLeft;
    this.btnRight = btnRight;
    this.container = container;
    this.ivCenter = ivCenter;
  }

  @NonNull
  public static LayoutToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutToolbarBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_toolbar, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutToolbarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutToolbarBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_toolbar, null, false, component);
  }

  public static LayoutToolbarBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static LayoutToolbarBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (LayoutToolbarBinding)bind(component, view, com.iindicar.indicar.R.layout.layout_toolbar);
  }
}
