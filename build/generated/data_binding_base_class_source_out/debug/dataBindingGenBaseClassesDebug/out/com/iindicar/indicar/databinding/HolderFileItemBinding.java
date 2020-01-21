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
import com.iindicar.indicar.model.BoardFile;

public abstract class HolderFileItemBinding extends ViewDataBinding {
  @Bindable
  protected BoardFile mItem;

  protected HolderFileItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setItem(@Nullable BoardFile item);

  @Nullable
  public BoardFile getItem() {
    return mItem;
  }

  @NonNull
  public static HolderFileItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderFileItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderFileItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_file_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderFileItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderFileItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderFileItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_file_item, null, false, component);
  }

  public static HolderFileItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderFileItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderFileItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_file_item);
  }
}
