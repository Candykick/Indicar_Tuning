package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public abstract class HolderImageBannerItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageView image;

  protected HolderImageBannerItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView image) {
    super(_bindingComponent, _root, _localFieldCount);
    this.image = image;
  }

  @NonNull
  public static HolderImageBannerItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderImageBannerItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderImageBannerItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_image_banner_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderImageBannerItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderImageBannerItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderImageBannerItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_image_banner_item, null, false, component);
  }

  public static HolderImageBannerItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderImageBannerItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderImageBannerItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_image_banner_item);
  }
}
