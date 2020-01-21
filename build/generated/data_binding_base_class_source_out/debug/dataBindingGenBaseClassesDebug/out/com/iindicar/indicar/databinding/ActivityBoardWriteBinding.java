package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public abstract class ActivityBoardWriteBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appbar;

  @NonNull
  public final FrameLayout fragmentContainer;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final ImageView toolbarImage;

  @NonNull
  public final Toolbar toolbarLayout;

  protected ActivityBoardWriteBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, AppBarLayout appbar, FrameLayout fragmentContainer,
      LayoutToolbarBinding toolbar, ImageView toolbarImage, Toolbar toolbarLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appbar = appbar;
    this.fragmentContainer = fragmentContainer;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.toolbarImage = toolbarImage;
    this.toolbarLayout = toolbarLayout;
  }

  @NonNull
  public static ActivityBoardWriteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityBoardWriteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityBoardWriteBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_board_write, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityBoardWriteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityBoardWriteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityBoardWriteBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_board_write, null, false, component);
  }

  public static ActivityBoardWriteBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityBoardWriteBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityBoardWriteBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_board_write);
  }
}
