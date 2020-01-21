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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.utils.EmptyRecyclerView;
import com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity;

public abstract class LayoutBoardDetailBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnMenu;

  @NonNull
  public final HolderCommentBoxBinding commentBox;

  @NonNull
  public final LinearLayout commentMore;

  @NonNull
  public final LinearLayout emptyView;

  @NonNull
  public final EmptyRecyclerView recyclerComment;

  @NonNull
  public final RecyclerView recyclerFile;

  @Bindable
  protected BoardDetailActivity mActivity;

  @Bindable
  protected Board mItem;

  protected LayoutBoardDetailBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnMenu, HolderCommentBoxBinding commentBox,
      LinearLayout commentMore, LinearLayout emptyView, EmptyRecyclerView recyclerComment,
      RecyclerView recyclerFile) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnMenu = btnMenu;
    this.commentBox = commentBox;
    setContainedBinding(this.commentBox);;
    this.commentMore = commentMore;
    this.emptyView = emptyView;
    this.recyclerComment = recyclerComment;
    this.recyclerFile = recyclerFile;
  }

  public abstract void setActivity(@Nullable BoardDetailActivity activity);

  @Nullable
  public BoardDetailActivity getActivity() {
    return mActivity;
  }

  public abstract void setItem(@Nullable Board item);

  @Nullable
  public Board getItem() {
    return mItem;
  }

  @NonNull
  public static LayoutBoardDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutBoardDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutBoardDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_board_detail, root, attachToRoot, component);
  }

  @NonNull
  public static LayoutBoardDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static LayoutBoardDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<LayoutBoardDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.layout_board_detail, null, false, component);
  }

  public static LayoutBoardDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static LayoutBoardDetailBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (LayoutBoardDetailBinding)bind(component, view, com.iindicar.indicar.R.layout.layout_board_detail);
  }
}
