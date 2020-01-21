package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public abstract class FragmentWriteContentBinding extends ViewDataBinding {
  @NonNull
  public final Button btnCancel;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final RecyclerView recyclerContent;

  @NonNull
  public final LinearLayout rootView;

  protected FragmentWriteContentBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button btnCancel, Button btnNext, RecyclerView recyclerContent,
      LinearLayout rootView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnCancel = btnCancel;
    this.btnNext = btnNext;
    this.recyclerContent = recyclerContent;
    this.rootView = rootView;
  }

  @NonNull
  public static FragmentWriteContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentWriteContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentWriteContentBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_write_content, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentWriteContentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentWriteContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentWriteContentBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_write_content, null, false, component);
  }

  public static FragmentWriteContentBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentWriteContentBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentWriteContentBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_write_content);
  }
}
