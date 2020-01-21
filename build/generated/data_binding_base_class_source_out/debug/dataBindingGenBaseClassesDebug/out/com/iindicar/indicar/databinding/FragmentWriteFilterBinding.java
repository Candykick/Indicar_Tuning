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
import android.widget.ImageButton;
import android.widget.TextView;
import com.iindicar.indicar.utils.EmptyRecyclerView;
import com.iindicar.indicar.view.community.boardWrite.WriteFilterPresenter;

public abstract class FragmentWriteFilterBinding extends ViewDataBinding {
  @NonNull
  public final ImageButton btnAlbum;

  @NonNull
  public final ImageButton btnCamera;

  @NonNull
  public final Button btnCancel;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final TextView emptyView;

  @NonNull
  public final RecyclerView recyclerBoardFilter;

  @NonNull
  public final EmptyRecyclerView recyclerImages;

  @Bindable
  protected WriteFilterPresenter mPresenter;

  protected FragmentWriteFilterBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageButton btnAlbum, ImageButton btnCamera, Button btnCancel,
      Button btnNext, TextView emptyView, RecyclerView recyclerBoardFilter,
      EmptyRecyclerView recyclerImages) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnAlbum = btnAlbum;
    this.btnCamera = btnCamera;
    this.btnCancel = btnCancel;
    this.btnNext = btnNext;
    this.emptyView = emptyView;
    this.recyclerBoardFilter = recyclerBoardFilter;
    this.recyclerImages = recyclerImages;
  }

  public abstract void setPresenter(@Nullable WriteFilterPresenter presenter);

  @Nullable
  public WriteFilterPresenter getPresenter() {
    return mPresenter;
  }

  @NonNull
  public static FragmentWriteFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentWriteFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentWriteFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_write_filter, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentWriteFilterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentWriteFilterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentWriteFilterBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_write_filter, null, false, component);
  }

  public static FragmentWriteFilterBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentWriteFilterBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentWriteFilterBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_write_filter);
  }
}
