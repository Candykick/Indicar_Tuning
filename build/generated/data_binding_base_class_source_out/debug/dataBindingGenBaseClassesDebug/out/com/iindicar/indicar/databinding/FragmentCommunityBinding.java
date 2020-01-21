package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class FragmentCommunityBinding extends ViewDataBinding {
  @NonNull
  public final FloatingActionButton fabWrite;

  @NonNull
  public final TabLayout tabLayoutCommunity;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final ViewPager viewPagerBoard;

  protected FragmentCommunityBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, FloatingActionButton fabWrite, TabLayout tabLayoutCommunity,
      LayoutToolbarBinding toolbar, ViewPager viewPagerBoard) {
    super(_bindingComponent, _root, _localFieldCount);
    this.fabWrite = fabWrite;
    this.tabLayoutCommunity = tabLayoutCommunity;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.viewPagerBoard = viewPagerBoard;
  }

  @NonNull
  public static FragmentCommunityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentCommunityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentCommunityBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_community, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentCommunityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentCommunityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentCommunityBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_community, null, false, component);
  }

  public static FragmentCommunityBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentCommunityBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentCommunityBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_community);
  }
}
