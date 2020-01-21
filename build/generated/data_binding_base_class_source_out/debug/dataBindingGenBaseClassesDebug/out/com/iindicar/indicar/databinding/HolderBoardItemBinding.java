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
import android.widget.ImageView;
import com.iindicar.indicar.model.Board;

public abstract class HolderBoardItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageView ivNotice;

  @Bindable
  protected Board mItem;

  protected HolderBoardItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView ivNotice) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivNotice = ivNotice;
  }

  public abstract void setItem(@Nullable Board item);

  @Nullable
  public Board getItem() {
    return mItem;
  }

  @NonNull
  public static HolderBoardItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderBoardItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderBoardItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_board_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderBoardItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderBoardItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderBoardItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_board_item, null, false, component);
  }

  public static HolderBoardItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderBoardItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderBoardItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_board_item);
  }
}
