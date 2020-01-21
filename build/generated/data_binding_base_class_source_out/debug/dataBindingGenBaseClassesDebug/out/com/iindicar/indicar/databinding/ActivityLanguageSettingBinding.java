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
import android.widget.Button;
import com.iindicar.indicar.view.account.profile.LanguageSettingActivity;

public abstract class ActivityLanguageSettingBinding extends ViewDataBinding {
  @NonNull
  public final Button btnDone;

  @NonNull
  public final RecyclerView recyclerLanguage;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  @Bindable
  protected LanguageSettingActivity mActivity;

  protected ActivityLanguageSettingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button btnDone, RecyclerView recyclerLanguage,
      LayoutTextToolbarBinding toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnDone = btnDone;
    this.recyclerLanguage = recyclerLanguage;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
  }

  public abstract void setActivity(@Nullable LanguageSettingActivity activity);

  @Nullable
  public LanguageSettingActivity getActivity() {
    return mActivity;
  }

  @NonNull
  public static ActivityLanguageSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLanguageSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLanguageSettingBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_language_setting, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLanguageSettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLanguageSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLanguageSettingBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_language_setting, null, false, component);
  }

  public static ActivityLanguageSettingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityLanguageSettingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityLanguageSettingBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_language_setting);
  }
}
