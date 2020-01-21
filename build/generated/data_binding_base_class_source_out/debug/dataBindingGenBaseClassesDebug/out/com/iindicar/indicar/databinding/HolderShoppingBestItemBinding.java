package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
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
import com.iindicar.indicar.model.Product;

public abstract class HolderShoppingBestItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageView ivBest;

  @NonNull
  public final ImageView ivNotice;

  @NonNull
  public final TextView ivSoldOut;

  @NonNull
  public final TextView tvOriginalPrice;

  @Bindable
  protected Product mItem;

  protected HolderShoppingBestItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView ivBest, ImageView ivNotice, TextView ivSoldOut,
      TextView tvOriginalPrice) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivBest = ivBest;
    this.ivNotice = ivNotice;
    this.ivSoldOut = ivSoldOut;
    this.tvOriginalPrice = tvOriginalPrice;
  }

  public abstract void setItem(@Nullable Product item);

  @Nullable
  public Product getItem() {
    return mItem;
  }

  @NonNull
  public static HolderShoppingBestItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderShoppingBestItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderShoppingBestItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_shopping_best_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderShoppingBestItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderShoppingBestItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderShoppingBestItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_shopping_best_item, null, false, component);
  }

  public static HolderShoppingBestItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderShoppingBestItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderShoppingBestItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_shopping_best_item);
  }
}
