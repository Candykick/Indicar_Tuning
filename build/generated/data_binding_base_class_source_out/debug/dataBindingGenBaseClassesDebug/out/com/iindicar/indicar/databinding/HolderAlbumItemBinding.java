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
import android.widget.TextView;
import com.iindicar.indicar.data.local.GalleryVO;

public abstract class HolderAlbumItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView btnIndex;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final View layoutSelected;

  @Bindable
  protected GalleryVO mItem;

  protected HolderAlbumItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView btnIndex, ImageView imageView, View layoutSelected) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnIndex = btnIndex;
    this.imageView = imageView;
    this.layoutSelected = layoutSelected;
  }

  public abstract void setItem(@Nullable GalleryVO item);

  @Nullable
  public GalleryVO getItem() {
    return mItem;
  }

  @NonNull
  public static HolderAlbumItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderAlbumItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderAlbumItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_album_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderAlbumItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderAlbumItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderAlbumItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_album_item, null, false, component);
  }

  public static HolderAlbumItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderAlbumItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderAlbumItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_album_item);
  }
}
