package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.iindicar.indicar.model.BoardFilterItem;

public abstract class HolderBoardFilterBinding extends ViewDataBinding {
  @NonNull
  public final FrameLayout itemFrame;

  @Bindable
  protected ObservableField mBoardType;

  @Bindable
  protected BoardFilterItem mItem;

  protected HolderBoardFilterBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, FrameLayout itemFrame) {
    super(_bindingComponent, _root, _localFieldCount);
    this.itemFrame = itemFrame;
  }

  public abstract void setBoardType(@Nullable ObservableField boardType);

  @Nullable
  public ObservableField getBoardType() {
    return mBoardType;
  }

  public abstract void setItem(@Nullable BoardFilterItem item);

  @Nullable
  public BoardFilterItem getItem() {
    return mItem;
  }

  @NonNull
  public static HolderBoardFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderBoardFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderBoardFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_board_filter, root, attachToRoot, component);
  }

  @NonNull
  public static HolderBoardFilterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderBoardFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderBoardFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_board_filter, null, false, component);
  }

  public static HolderBoardFilterBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderBoardFilterBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderBoardFilterBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_board_filter);
  }
}
