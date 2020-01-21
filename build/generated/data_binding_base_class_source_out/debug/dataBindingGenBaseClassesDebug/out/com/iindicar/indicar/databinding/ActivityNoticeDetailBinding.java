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
import com.iindicar.indicar.model.Notice;

public abstract class ActivityNoticeDetailBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView recyclerFile;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  @Bindable
  protected Notice mItem;

  protected ActivityNoticeDetailBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView recyclerFile, LayoutTextToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerFile = recyclerFile;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  public abstract void setItem(@Nullable Notice item);

  @Nullable
  public Notice getItem() {
    return mItem;
  }

  @NonNull
  public static ActivityNoticeDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNoticeDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNoticeDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_notice_detail, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityNoticeDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityNoticeDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityNoticeDetailBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_notice_detail, null, false, component);
  }

  public static ActivityNoticeDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityNoticeDetailBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityNoticeDetailBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_notice_detail);
  }
}
