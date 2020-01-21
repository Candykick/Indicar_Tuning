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
import android.widget.ImageView;
import com.iindicar.indicar.model.Notice;

public abstract class HolderNoticeItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageView ivNotice;

  @Bindable
  protected Notice mItem;

  protected HolderNoticeItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView ivNotice) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ivNotice = ivNotice;
  }

  public abstract void setItem(@Nullable Notice item);

  @Nullable
  public Notice getItem() {
    return mItem;
  }

  @NonNull
  public static HolderNoticeItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderNoticeItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderNoticeItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_notice_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderNoticeItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderNoticeItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderNoticeItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_notice_item, null, false, component);
  }

  public static HolderNoticeItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderNoticeItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderNoticeItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_notice_item);
  }
}
