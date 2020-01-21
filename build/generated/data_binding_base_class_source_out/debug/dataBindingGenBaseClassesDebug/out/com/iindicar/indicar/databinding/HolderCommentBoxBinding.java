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
import android.widget.TextView;

public abstract class HolderCommentBoxBinding extends ViewDataBinding {
  @NonNull
  public final TextView btnSubmit;

  @NonNull
  public final EditText etComment;

  protected HolderCommentBoxBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView btnSubmit, EditText etComment) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSubmit = btnSubmit;
    this.etComment = etComment;
  }

  @NonNull
  public static HolderCommentBoxBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderCommentBoxBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderCommentBoxBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_comment_box, root, attachToRoot, component);
  }

  @NonNull
  public static HolderCommentBoxBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderCommentBoxBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderCommentBoxBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_comment_box, null, false, component);
  }

  public static HolderCommentBoxBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderCommentBoxBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderCommentBoxBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_comment_box);
  }
}
