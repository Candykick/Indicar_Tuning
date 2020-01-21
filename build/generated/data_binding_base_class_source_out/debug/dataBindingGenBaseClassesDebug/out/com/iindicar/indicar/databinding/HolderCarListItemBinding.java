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
import com.iindicar.indicar.model.Car;

public abstract class HolderCarListItemBinding extends ViewDataBinding {
  @Bindable
  protected Car mItem;

  protected HolderCarListItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setItem(@Nullable Car item);

  @Nullable
  public Car getItem() {
    return mItem;
  }

  @NonNull
  public static HolderCarListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderCarListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderCarListItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_car_list_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderCarListItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderCarListItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderCarListItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_car_list_item, null, false, component);
  }

  public static HolderCarListItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderCarListItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderCarListItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_car_list_item);
  }
}
