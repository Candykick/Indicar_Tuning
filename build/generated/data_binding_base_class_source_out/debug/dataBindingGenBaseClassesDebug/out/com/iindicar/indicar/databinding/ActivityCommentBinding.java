package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iindicar.indicar.view.community.comment.CommentPresenter;

public abstract class ActivityCommentBinding extends ViewDataBinding {
  @NonNull
  public final HolderCommentBoxBinding commentBox;

  @NonNull
  public final RecyclerView recyclerComment;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @Bindable
  protected CommentPresenter mPresenter;

  protected ActivityCommentBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, HolderCommentBoxBinding commentBox, RecyclerView recyclerComment,
      LayoutToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.commentBox = commentBox;
    setContainedBinding(this.commentBox);;
    this.recyclerComment = recyclerComment;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  public abstract void setPresenter(@Nullable CommentPresenter presenter);

  @Nullable
  public CommentPresenter getPresenter() {
    return mPresenter;
  }

  @NonNull
  public static ActivityCommentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCommentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCommentBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_comment, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCommentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCommentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCommentBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_comment, null, false, component);
  }

  public static ActivityCommentBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityCommentBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityCommentBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_comment);
  }
}
