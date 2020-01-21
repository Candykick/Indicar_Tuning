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
import android.widget.ImageButton;
import android.widget.ImageView;
import com.iindicar.indicar.view.main.tutorial.TutorialFragment;

public abstract class FragmentTutorialBinding extends ViewDataBinding {
  @NonNull
  public final ImageView imageGif;

  @NonNull
  public final ImageButton imageTab;

  @Bindable
  protected TutorialFragment mFragment;

  protected FragmentTutorialBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView imageGif, ImageButton imageTab) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageGif = imageGif;
    this.imageTab = imageTab;
  }

  public abstract void setFragment(@Nullable TutorialFragment fragment);

  @Nullable
  public TutorialFragment getFragment() {
    return mFragment;
  }

  @NonNull
  public static FragmentTutorialBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTutorialBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTutorialBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_tutorial, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTutorialBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentTutorialBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentTutorialBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_tutorial, null, false, component);
  }

  public static FragmentTutorialBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentTutorialBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentTutorialBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_tutorial);
  }
}
