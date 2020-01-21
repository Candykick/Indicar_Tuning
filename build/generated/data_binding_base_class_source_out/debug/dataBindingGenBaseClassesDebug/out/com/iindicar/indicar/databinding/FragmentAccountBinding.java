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

public abstract class FragmentAccountBinding extends ViewDataBinding {
  @NonNull
  public final TabLayout tabLayoutAccount;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final ViewPager viewPagerAccount;

  protected FragmentAccountBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TabLayout tabLayoutAccount, LayoutToolbarBinding toolbar,
      ViewPager viewPagerAccount) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tabLayoutAccount = tabLayoutAccount;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.viewPagerAccount = viewPagerAccount;
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentAccountBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_account, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentAccountBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_account, null, false, component);
  }

  public static FragmentAccountBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentAccountBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentAccountBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_account);
  }
}
