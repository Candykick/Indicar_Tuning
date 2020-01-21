package com.iindicar.indicar.databinding;

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
import android.widget.TextView;

public abstract class ActivityAlbumBinding extends ViewDataBinding {
  @NonNull
  public final Button btnDone;

  @NonNull
  public final RecyclerView recyclerAlbum;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final TextView tvTotalCount;

  protected ActivityAlbumBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button btnDone, RecyclerView recyclerAlbum,
      LayoutToolbarBinding toolbar, TextView tvTotalCount) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnDone = btnDone;
    this.recyclerAlbum = recyclerAlbum;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.tvTotalCount = tvTotalCount;
  }

  @NonNull
  public static ActivityAlbumBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityAlbumBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityAlbumBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_album, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAlbumBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityAlbumBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityAlbumBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_album, null, false, component);
  }

  public static ActivityAlbumBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityAlbumBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityAlbumBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_album);
  }
}
