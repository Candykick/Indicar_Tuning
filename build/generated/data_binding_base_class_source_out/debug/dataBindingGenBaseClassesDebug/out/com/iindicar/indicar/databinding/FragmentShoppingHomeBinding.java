package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.iindicar.indicar.utils.EmptyRecyclerView;
import com.iindicar.indicar.view.shopping.shoppingHome.ShoppingHomePresenter;

public abstract class FragmentShoppingHomeBinding extends ViewDataBinding {
  @NonNull
  public final View dividerBest;

  @NonNull
  public final View dividerNew;

  @NonNull
  public final EmptyRecyclerView recyclerBest;

  @NonNull
  public final RecyclerView recyclerImage;

  @NonNull
  public final EmptyRecyclerView recyclerNew;

  @NonNull
  public final SwipeRefreshLayout swipeLayoutShopping;

  @NonNull
  public final TextView tvBest;

  @NonNull
  public final TextView tvBestMore;

  @NonNull
  public final TextView tvNew;

  @NonNull
  public final TextView tvNewMore;

  @Bindable
  protected ShoppingHomePresenter mPresenter;

  protected FragmentShoppingHomeBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, View dividerBest, View dividerNew, EmptyRecyclerView recyclerBest,
      RecyclerView recyclerImage, EmptyRecyclerView recyclerNew,
      SwipeRefreshLayout swipeLayoutShopping, TextView tvBest, TextView tvBestMore, TextView tvNew,
      TextView tvNewMore) {
    super(_bindingComponent, _root, _localFieldCount);
    this.dividerBest = dividerBest;
    this.dividerNew = dividerNew;
    this.recyclerBest = recyclerBest;
    this.recyclerImage = recyclerImage;
    this.recyclerNew = recyclerNew;
    this.swipeLayoutShopping = swipeLayoutShopping;
    this.tvBest = tvBest;
    this.tvBestMore = tvBestMore;
    this.tvNew = tvNew;
    this.tvNewMore = tvNewMore;
  }

  public abstract void setPresenter(@Nullable ShoppingHomePresenter presenter);

  @Nullable
  public ShoppingHomePresenter getPresenter() {
    return mPresenter;
  }

  @NonNull
  public static FragmentShoppingHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentShoppingHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentShoppingHomeBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_shopping_home, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentShoppingHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentShoppingHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentShoppingHomeBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_shopping_home, null, false, component);
  }

  public static FragmentShoppingHomeBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentShoppingHomeBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentShoppingHomeBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_shopping_home);
  }
}
