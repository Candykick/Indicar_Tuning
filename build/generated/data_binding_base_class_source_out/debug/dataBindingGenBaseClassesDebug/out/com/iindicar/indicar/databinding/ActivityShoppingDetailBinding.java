package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public abstract class ActivityShoppingDetailBinding extends ViewDataBinding {
  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  @NonNull
  public final WebView webView;

  protected ActivityShoppingDetailBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LayoutTextToolbarBinding toolbar, WebView webView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.webView = webView;
  }

  @NonNull
  public static ActivityShoppingDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityShoppingDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityShoppingDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_shopping_detail, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityShoppingDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityShoppingDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityShoppingDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_shopping_detail, null, false, component);
  }

  public static ActivityShoppingDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityShoppingDetailBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityShoppingDetailBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_shopping_detail);
  }
}
