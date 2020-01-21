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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.iindicar.indicar.view.main.join.JoinActivity;

public abstract class ActivityJoinBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout emailPanel;

  @NonNull
  public final EditText jiNAlertEmail1;

  @NonNull
  public final EditText jiNAlertEmail2;

  @NonNull
  public final Spinner jiNAlertSpinner;

  @NonNull
  public final TextView joinbtnEmailAuth;

  @NonNull
  public final LinearLayout joinbtnJoin;

  @NonNull
  public final TextView joinbtnResendEmail;

  @NonNull
  public final EditText joinetName;

  @NonNull
  public final LinearLayout loadingjoin;

  @NonNull
  public final LayoutTextToolbarBinding toolbar;

  @NonNull
  public final TextView tvab;

  @Bindable
  protected JoinActivity mActivity;

  protected ActivityJoinBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout emailPanel, EditText jiNAlertEmail1,
      EditText jiNAlertEmail2, Spinner jiNAlertSpinner, TextView joinbtnEmailAuth,
      LinearLayout joinbtnJoin, TextView joinbtnResendEmail, EditText joinetName,
      LinearLayout loadingjoin, LayoutTextToolbarBinding toolbar, TextView tvab) {
    super(_bindingComponent, _root, _localFieldCount);
    this.emailPanel = emailPanel;
    this.jiNAlertEmail1 = jiNAlertEmail1;
    this.jiNAlertEmail2 = jiNAlertEmail2;
    this.jiNAlertSpinner = jiNAlertSpinner;
    this.joinbtnEmailAuth = joinbtnEmailAuth;
    this.joinbtnJoin = joinbtnJoin;
    this.joinbtnResendEmail = joinbtnResendEmail;
    this.joinetName = joinetName;
    this.loadingjoin = loadingjoin;
    this.toolbar = toolbar;
    setContainedBinding(this.toolbar);;
    this.tvab = tvab;
  }

  public abstract void setActivity(@Nullable JoinActivity activity);

  @Nullable
  public JoinActivity getActivity() {
    return mActivity;
  }

  @NonNull
  public static ActivityJoinBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityJoinBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityJoinBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_join, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityJoinBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityJoinBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityJoinBinding>inflate(inflater, com.iindicar.indicar.R.layout.activity_join, null, false, component);
  }

  public static ActivityJoinBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityJoinBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityJoinBinding)bind(component, view, com.iindicar.indicar.R.layout.activity_join);
  }
}
