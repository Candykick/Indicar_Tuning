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

public abstract class ActivityNoticeBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView recyclerNotice;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  protected ActivityNoticeBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView recyclerNotice, LayoutTextToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerNotice = recyclerNotice;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  @NonNull
  public static ActivityNoticeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNoticeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNoticeBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_notice, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityNoticeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNoticeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNoticeBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_notice, null, false, component);
  }

  public static ActivityNoticeBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityNoticeBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityNoticeBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_notice);
  }
}
