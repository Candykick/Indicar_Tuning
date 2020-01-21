package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public abstract class ActivityCarFilterBinding extends ViewDataBinding {
  @NonNull
  public final ListView listView;

  @NonNull
  public final EditText searchText;

  @NonNull
  public final ImageView textCarfilterTitle;

  protected ActivityCarFilterBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ListView listView, EditText searchText, ImageView textCarfilterTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.listView = listView;
    this.searchText = searchText;
    this.textCarfilterTitle = textCarfilterTitle;
  }

  @NonNull
  public static ActivityCarFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCarFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCarFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_car_filter, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCarFilterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCarFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCarFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_car_filter, null, false, component);
  }

  public static ActivityCarFilterBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityCarFilterBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityCarFilterBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_car_filter);
  }
}
