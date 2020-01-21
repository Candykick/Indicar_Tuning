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
import android.widget.EditText;
import com.iindicar.indicar.model.BoardFile;

public abstract class HolderWriteContentBinding extends ViewDataBinding {
  @NonNull
  public final EditText etText;

  @Bindable
  protected BoardFile mItem;

  protected HolderWriteContentBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, EditText etText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.etText = etText;
  }

  public abstract void setItem(@Nullable BoardFile item);

  @Nullable
  public BoardFile getItem() {
    return mItem;
  }

  @NonNull
  public static HolderWriteContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderWriteContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderWriteContentBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_write_content, root, attachToRoot, component);
  }

  @NonNull
  public static HolderWriteContentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderWriteContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderWriteContentBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_write_content, null, false, component);
  }

  public static HolderWriteContentBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderWriteContentBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderWriteContentBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_write_content);
  }
}
