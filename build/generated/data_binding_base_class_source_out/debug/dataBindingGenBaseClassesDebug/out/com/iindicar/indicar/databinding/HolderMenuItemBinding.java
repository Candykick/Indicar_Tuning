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
import com.iindicar.indicar.view.main.MenuFragment;

public abstract class HolderMenuItemBinding extends ViewDataBinding {
  @Bindable
  protected MenuFragment.MenuItem mItem;

  protected HolderMenuItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setItem(@Nullable MenuFragment.MenuItem item);

  @Nullable
  public MenuFragment.MenuItem getItem() {
    return mItem;
  }

  @NonNull
  public static HolderMenuItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderMenuItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderMenuItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_menu_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderMenuItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderMenuItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderMenuItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_menu_item, null, false, component);
  }

  public static HolderMenuItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderMenuItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderMenuItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_menu_item);
  }
}
