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
import android.widget.TextView;

public abstract class LayoutTextToolbarBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnLeft;

  @NonNull
  public final ImageButton btnRight;

  @NonNull
  public final TextView tvCenter;

  protected LayoutTextToolbarBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnLeft, ImageButton btnRight, TextView tvCenter) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnLeft = btnLeft;
    this.btnRight = btnRight;
    this.tvCenter = tvCenter;
  }

  @NonNull
  public static LayoutTextToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutTextToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutTextToolbarBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_text_toolbar, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutTextToolbarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutTextToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutTextToolbarBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_text_toolbar, null, false, component);
  }

  public static LayoutTextToolbarBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static LayoutTextToolbarBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (LayoutTextToolbarBinding)bind(component, view, com.iindicar.indicar.R.layout.layout_text_toolbar);
  }
}
