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

public abstract class HolderShoppingItemBinding extends ViewDataBinding {
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

  protected HolderShoppingItemBinding(DataBindingComponent _bindingComponent, View _root,
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
  public static HolderShoppingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderShoppingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderShoppingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_shopping_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderShoppingItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderShoppingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderShoppingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_shopping_item, null, false, component);
  }

  public static HolderShoppingItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderShoppingItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderShoppingItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_shopping_item);
  }
}
