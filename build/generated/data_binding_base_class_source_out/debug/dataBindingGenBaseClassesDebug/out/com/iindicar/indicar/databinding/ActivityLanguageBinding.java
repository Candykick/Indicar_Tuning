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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iindicar.indicar.view.main.LanguageActivity;

public abstract class ActivityLanguageBinding extends ViewDataBinding {
  @NonNull
  public final Button btnLangEng;

  @NonNull
  public final Button btnLangKor;

  @NonNull
  public final Button btnLangNext;

  @NonNull
  public final Button btnLangPr;

  @NonNull
  public final Button btnLangRu;

  @NonNull
  public final Button btnLangSp;

  @NonNull
  public final LinearLayout llLang1;

  @NonNull
  public final LinearLayout llLang2;

  @NonNull
  public final TextView tvLangTitle;

  @Bindable
  protected LanguageActivity mActivity;

  protected ActivityLanguageBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button btnLangEng, Button btnLangKor, Button btnLangNext,
      Button btnLangPr, Button btnLangRu, Button btnLangSp, LinearLayout llLang1,
      LinearLayout llLang2, TextView tvLangTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnLangEng = btnLangEng;
    this.btnLangKor = btnLangKor;
    this.btnLangNext = btnLangNext;
    this.btnLangPr = btnLangPr;
    this.btnLangRu = btnLangRu;
    this.btnLangSp = btnLangSp;
    this.llLang1 = llLang1;
    this.llLang2 = llLang2;
    this.tvLangTitle = tvLangTitle;
  }

  public abstract void setActivity(@Nullable LanguageActivity activity);

  @Nullable
  public LanguageActivity getActivity() {
    return mActivity;
  }

  @NonNull
  public static ActivityLanguageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLanguageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLanguageBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_language, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLanguageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityLanguageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityLanguageBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_language, null, false, component);
  }

  public static ActivityLanguageBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityLanguageBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityLanguageBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_language);
  }
}
