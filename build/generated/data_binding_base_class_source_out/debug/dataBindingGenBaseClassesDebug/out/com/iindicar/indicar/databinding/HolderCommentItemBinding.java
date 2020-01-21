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
import android.widget.LinearLayout;
import com.iindicar.indicar.model.Comment;

public abstract class HolderCommentItemBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout itemPanel;

  @Bindable
  protected Comment mItem;

  protected HolderCommentItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout itemPanel) {
    super(_bindingComponent, _root, _localFieldCount);
    this.itemPanel = itemPanel;
  }

  public abstract void setItem(@Nullable Comment item);

  @Nullable
  public Comment getItem() {
    return mItem;
  }

  @NonNull
  public static HolderCommentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderCommentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderCommentItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_comment_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderCommentItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderCommentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderCommentItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_comment_item, null, false, component);
  }

  public static HolderCommentItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderCommentItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderCommentItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_comment_item);
  }
}
