package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.view.main.MenuFragment;

public abstract class FragmentMenuBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout container;

  @NonNull
  public final RecyclerView recyclerDrawerMenu;

  @NonNull
  public final LinearLayout userPanel;

  @Bindable
  protected MenuFragment mFragment;

  @Bindable
  protected User mUser;

  protected FragmentMenuBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout container, RecyclerView recyclerDrawerMenu,
      LinearLayout userPanel) {
    super(_bindingComponent, _root, _localFieldCount);
    this.container = container;
    this.recyclerDrawerMenu = recyclerDrawerMenu;
    this.userPanel = userPanel;
  }

  public abstract void setFragment(@Nullable MenuFragment fragment);

  @Nullable
  public MenuFragment getFragment() {
    return mFragment;
  }

  public abstract void setUser(@Nullable User user);

  @Nullable
  public User getUser() {
    return mUser;
  }

  @NonNull
  public static FragmentMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentMenuBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_menu, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentMenuBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_menu, null, false, component);
  }

  public static FragmentMenuBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentMenuBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentMenuBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_menu);
  }
}
