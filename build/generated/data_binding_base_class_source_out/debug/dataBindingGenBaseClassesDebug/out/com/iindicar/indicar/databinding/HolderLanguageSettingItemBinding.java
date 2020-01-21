package com.iindicar.indicar.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iindicar.indicar.view.account.profile.LanguageSettingActivity;

public abstract class HolderLanguageSettingItemBinding extends ViewDataBinding {
  @Bindable
  protected LanguageSettingActivity.LanguageSettingItem mItem;

  @Bindable
  protected ObservableField mLanguage;

  protected HolderLanguageSettingItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setItem(@Nullable LanguageSettingActivity.LanguageSettingItem item);

  @Nullable
  public LanguageSettingActivity.LanguageSettingItem getItem() {
    return mItem;
  }

  public abstract void setLanguage(@Nullable ObservableField language);

  @Nullable
  public ObservableField getLanguage() {
    return mLanguage;
  }

  @NonNull
  public static HolderLanguageSettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderLanguageSettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderLanguageSettingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_language_setting_item, root, attachToRoot, component);
  }

  @NonNull
  public static HolderLanguageSettingItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static HolderLanguageSettingItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<HolderLanguageSettingItemBinding>inflate(inflater, com.iindicar.indicar.R.layout.holder_language_setting_item, null, false, component);
  }

  public static HolderLanguageSettingItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static HolderLanguageSettingItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (HolderLanguageSettingItemBinding)bind(component, view, com.iindicar.indicar.R.layout.holder_language_setting_item);
  }
}
