package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.iindicar.indicar.utils.EmptyRecyclerView;
import com.iindicar.indicar.view.community.boardList.BoardListPresenter;

public abstract class FragmentBoardListBinding extends ViewDataBinding {
  @NonNull
  public final TextView empty;

  @NonNull
  public final EmptyRecyclerView recyclerBoardList;

  @NonNull
  public final SwipeRefreshLayout swipeLayoutBoard;

  @Bindable
  protected BoardListPresenter mPresenter;

  protected FragmentBoardListBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView empty, EmptyRecyclerView recyclerBoardList,
      SwipeRefreshLayout swipeLayoutBoard) {
    super(_bindingComponent, _root, _localFieldCount);
    this.empty = empty;
    this.recyclerBoardList = recyclerBoardList;
    this.swipeLayoutBoard = swipeLayoutBoard;
  }

  public abstract void setPresenter(@Nullable BoardListPresenter presenter);

  @Nullable
  public BoardListPresenter getPresenter() {
    return mPresenter;
  }

  @NonNull
  public static FragmentBoardListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentBoardListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentBoardListBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_board_list, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentBoardListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentBoardListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentBoardListBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_board_list, null, false, component);
  }

  public static FragmentBoardListBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentBoardListBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentBoardListBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_board_list);
  }
}
