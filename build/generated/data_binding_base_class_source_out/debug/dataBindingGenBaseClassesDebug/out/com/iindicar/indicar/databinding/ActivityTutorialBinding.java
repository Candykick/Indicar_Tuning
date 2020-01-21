package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.iindicar.indicar.view.main.tutorial.TutorialActivity;

public abstract class ActivityTutorialBinding extends ViewDataBinding {
  @NonNull
  public final Button btnNext;

  @NonNull
  public final Button btnSkip;

  @NonNull
  public final ViewPager viewPagerWelcome;

  @Bindable
  protected TutorialActivity mActivity;

  protected ActivityTutorialBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button btnNext, Button btnSkip, ViewPager viewPagerWelcome) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnNext = btnNext;
    this.btnSkip = btnSkip;
    this.viewPagerWelcome = viewPagerWelcome;
  }

  public abstract void setActivity(@Nullable TutorialActivity activity);

  @Nullable
  public TutorialActivity getActivity() {
    return mActivity;
  }

  @NonNull
  public static ActivityTutorialBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityTutorialBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityTutorialBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_tutorial, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityTutorialBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityTutorialBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityTutorialBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_tutorial, null, false, component);
  }

  public static ActivityTutorialBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityTutorialBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityTutorialBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_tutorial);
  }
}
