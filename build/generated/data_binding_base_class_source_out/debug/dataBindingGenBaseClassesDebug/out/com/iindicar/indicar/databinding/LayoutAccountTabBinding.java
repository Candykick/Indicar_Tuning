package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class LayoutAccountTabBinding extends ViewDataBinding {
  @NonNull
  public final ImageView ivMore;

  @NonNull
  public final TextView tvCount;

  @NonNull
  public final TextView tvTitle;

  protected LayoutAccountTabBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView ivMore, TextView tvCount, TextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivMore = ivMore;
    this.tvCount = tvCount;
    this.tvTitle = tvTitle;
  }

  @NonNull
  public static LayoutAccountTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutAccountTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutAccountTabBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_account_tab, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutAccountTabBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutAccountTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutAccountTabBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_account_tab, null, false, component);
  }

  public static LayoutAccountTabBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static LayoutAccountTabBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (LayoutAccountTabBinding)bind(component, view, com.iindicar.indicar.R.layout.layout_account_tab);
  }
}
