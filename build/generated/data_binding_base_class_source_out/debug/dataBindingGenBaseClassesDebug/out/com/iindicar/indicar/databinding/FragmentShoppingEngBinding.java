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

public abstract class FragmentShoppingEngBinding extends ViewDataBinding {
  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final ImageView viewPager;

  protected FragmentShoppingEngBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LayoutToolbarBinding toolbar, ImageView viewPager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.viewPager = viewPager;
  }

  @NonNull
  public static FragmentShoppingEngBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentShoppingEngBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentShoppingEngBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_shopping_eng, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentShoppingEngBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentShoppingEngBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentShoppingEngBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_shopping_eng, null, false, component);
  }

  public static FragmentShoppingEngBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentShoppingEngBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentShoppingEngBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_shopping_eng);
  }
}
