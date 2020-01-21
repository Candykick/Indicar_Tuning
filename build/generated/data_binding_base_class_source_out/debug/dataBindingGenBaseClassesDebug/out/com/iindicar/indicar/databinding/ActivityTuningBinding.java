package com.iindicar.indicar.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.iindicar.indicar.utils.EmptyRecyclerView;

public abstract class ActivityTuningBinding extends ViewDataBinding {
  @NonNull
  public final Button btnSubmit;

  @NonNull
  public final ImageButton btnTLeft;

  @NonNull
  public final ImageButton btnTRight;

  @NonNull
  public final TextView emptyView;

  @NonNull
  public final EditText etSearch;

  @NonNull
  public final EmptyRecyclerView recyclerCarList;

  @NonNull
  public final ImageView tabLogo;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  protected ActivityTuningBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button btnSubmit, ImageButton btnTLeft, ImageButton btnTRight,
      TextView emptyView, EditText etSearch, EmptyRecyclerView recyclerCarList, ImageView tabLogo,
      LayoutToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSubmit = btnSubmit;
    this.btnTLeft = btnTLeft;
    this.btnTRight = btnTRight;
    this.emptyView = emptyView;
    this.etSearch = etSearch;
    this.recyclerCarList = recyclerCarList;
    this.tabLogo = tabLogo;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  @NonNull
  public static ActivityTuningBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityTuningBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityTuningBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_tuning, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityTuningBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityTuningBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityTuningBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_tuning, null, false, component);
  }

  public static ActivityTuningBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityTuningBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityTuningBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_tuning);
  }
}
