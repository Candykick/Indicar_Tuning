package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.iindicar.indicar.utils.SwipeViewPager;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final FrameLayout drawerMenu;

  @NonNull
  public final TabLayout tabLayoutMain;

  @NonNull
  public final SwipeViewPager viewPagerMain;

  protected ActivityMainBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, DrawerLayout drawerLayout, FrameLayout drawerMenu,
      TabLayout tabLayoutMain, SwipeViewPager viewPagerMain) {
    super(_bindingComponent, _root, _localFieldCount);
    this.drawerLayout = drawerLayout;
    this.drawerMenu = drawerMenu;
    this.tabLayoutMain = tabLayoutMain;
    this.viewPagerMain = viewPagerMain;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityMainBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityMainBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_main);
  }
}
