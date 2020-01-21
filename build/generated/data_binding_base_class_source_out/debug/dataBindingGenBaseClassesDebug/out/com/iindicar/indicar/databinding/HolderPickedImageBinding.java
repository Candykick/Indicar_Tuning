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
import com.iindicar.indicar.model.BoardFile;

public abstract class HolderPickedImageBinding extends ViewDataBinding {
  @NonNull
  public final ImageView btnRemove;

  @Bindable
  protected BoardFile mItem;

  protected HolderPickedImageBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView btnRemove) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnRemove = btnRemove;
  }

  public abstract void setItem(@Nullable BoardFile item);

  @Nullable
  public BoardFile getItem() {
    return mItem;
  }

  @NonNull
  public static HolderPickedImageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderPickedImageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderPickedImageBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_picked_image, root, attachToRoot, component);
  }

  @NonNull
  public static HolderPickedImageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderPickedImageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderPickedImageBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_picked_image, null, false, component);
  }

  public static HolderPickedImageBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderPickedImageBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderPickedImageBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_picked_image);
  }
}
