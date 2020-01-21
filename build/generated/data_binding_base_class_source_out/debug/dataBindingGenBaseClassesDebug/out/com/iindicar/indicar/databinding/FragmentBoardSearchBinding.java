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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.iindicar.indicar.view.community.boardList.BoardSearchPresenter;

public abstract class FragmentBoardSearchBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnFilter;

  @NonNull
  public final Button btnFilterAll;

  @NonNull
  public final Button btnSubmit;

  @NonNull
  public final EditText etSearch;

  @NonNull
  public final LinearLayout filterPanel;

  @NonNull
  public final RecyclerView recyclerBoardList;

  @Bindable
  protected BoardSearchPresenter mPresenter;

  protected FragmentBoardSearchBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnFilter, Button btnFilterAll, Button btnSubmit,
      EditText etSearch, LinearLayout filterPanel, RecyclerView recyclerBoardList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnFilter = btnFilter;
    this.btnFilterAll = btnFilterAll;
    this.btnSubmit = btnSubmit;
    this.etSearch = etSearch;
    this.filterPanel = filterPanel;
    this.recyclerBoardList = recyclerBoardList;
  }

  public abstract void setPresenter(@Nullable BoardSearchPresenter presenter);

  @Nullable
  public BoardSearchPresenter getPresenter() {
    return mPresenter;
  }

  @NonNull
  public static FragmentBoardSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentBoardSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentBoardSearchBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_board_search, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentBoardSearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentBoardSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentBoardSearchBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_board_search, null, false, component);
  }

  public static FragmentBoardSearchBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentBoardSearchBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentBoardSearchBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_board_search);
  }
}
