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
import com.iindicar.indicar.model.Board;

public abstract class ActivityBoardDetailBinding extends ViewDataBinding {
  @NonNull
  public final HolderBoardHeaderBinding boardHeader;

  @NonNull
  public final LayoutBoardDetailBinding content;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  @Bindable
  protected Board mItem;

  protected ActivityBoardDetailBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, HolderBoardHeaderBinding boardHeader, LayoutBoardDetailBinding content,
      LayoutTextToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.boardHeader = boardHeader;
    setContainedBinding(this.boardHeader);;
    this.content = content;
    setContainedBinding(this.content);;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  public abstract void setItem(@Nullable Board item);

  @Nullable
  public Board getItem() {
    return mItem;
  }

  @NonNull
  public static ActivityBoardDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityBoardDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityBoardDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_board_detail, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityBoardDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityBoardDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityBoardDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_board_detail, null, false, component);
  }

  public static ActivityBoardDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityBoardDetailBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityBoardDetailBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_board_detail);
  }
}
