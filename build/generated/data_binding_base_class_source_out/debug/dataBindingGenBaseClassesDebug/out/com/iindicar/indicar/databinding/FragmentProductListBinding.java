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
import com.iindicar.indicar.view.shopping.shoppingList.ProductListPresenter;

public abstract class FragmentProductListBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView recyclerProduct;

  @NonNull
  public final SwipeRefreshLayout swipeLayout;

  @Bindable
  protected ProductListPresenter mPresenter;

  protected FragmentProductListBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView recyclerProduct, SwipeRefreshLayout swipeLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerProduct = recyclerProduct;
    this.swipeLayout = swipeLayout;
  }

  public abstract void setPresenter(@Nullable ProductListPresenter presenter);

  @Nullable
  public ProductListPresenter getPresenter() {
    return mPresenter;
  }

  @NonNull
  public static FragmentProductListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentProductListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentProductListBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_product_list, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentProductListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentProductListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentProductListBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_product_list, null, false, component);
  }

  public static FragmentProductListBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentProductListBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentProductListBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_product_list);
  }
}
