package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentShoppingBinding extends ViewDataBinding {
  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final ViewPager viewPager;

  protected FragmentShoppingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TabLayout tabLayout, LayoutToolbarBinding toolbar,
      ViewPager viewPager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tabLayout = tabLayout;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.viewPager = viewPager;
  }

  @NonNull
  public static FragmentShoppingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentShoppingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentShoppingBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_shopping, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentShoppingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentShoppingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentShoppingBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_shopping, null, false, component);
  }

  public static FragmentShoppingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentShoppingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentShoppingBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_shopping);
  }
}
