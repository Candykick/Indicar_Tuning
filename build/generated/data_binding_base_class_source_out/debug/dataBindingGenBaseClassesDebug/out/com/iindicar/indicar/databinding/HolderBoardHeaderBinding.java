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
import android.widget.ImageButton;
import com.iindicar.indicar.model.Board;

public abstract class HolderBoardHeaderBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnComment;

  @NonNull
  public final ImageButton btnLike;

  @Bindable
  protected Board mItem;

  protected HolderBoardHeaderBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnComment, ImageButton btnLike) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnComment = btnComment;
    this.btnLike = btnLike;
  }

  public abstract void setItem(@Nullable Board item);

  @Nullable
  public Board getItem() {
    return mItem;
  }

  @NonNull
  public static HolderBoardHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderBoardHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderBoardHeaderBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_board_header, root, attachToRoot, component);
  }

  @NonNull
  public static HolderBoardHeaderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderBoardHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderBoardHeaderBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_board_header, null, false, component);
  }

  public static HolderBoardHeaderBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderBoardHeaderBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderBoardHeaderBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_board_header);
  }
}
