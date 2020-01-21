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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.view.account.profile.ProfileFragment;

public abstract class FragmentProfileBinding extends ViewDataBinding {
  @NonNull
  public final TextView apTvEmail;

  @NonNull
  public final LinearLayout btnAccount;

  @NonNull
  public final LinearLayout btnFacebook;

  @NonNull
  public final LinearLayout btnLanguage;

  @NonNull
  public final LinearLayout btnNotification;

  @NonNull
  public final LinearLayout btnShare;

  @NonNull
  public final LinearLayout btnShopping;

  @NonNull
  public final LinearLayout btnYoutube;

  @Bindable
  protected ProfileFragment mFragment;

  @Bindable
  protected User mUser;

  protected FragmentProfileBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView apTvEmail, LinearLayout btnAccount, LinearLayout btnFacebook,
      LinearLayout btnLanguage, LinearLayout btnNotification, LinearLayout btnShare,
      LinearLayout btnShopping, LinearLayout btnYoutube) {
    super(_bindingComponent, _root, _localFieldCount);
    this.apTvEmail = apTvEmail;
    this.btnAccount = btnAccount;
    this.btnFacebook = btnFacebook;
    this.btnLanguage = btnLanguage;
    this.btnNotification = btnNotification;
    this.btnShare = btnShare;
    this.btnShopping = btnShopping;
    this.btnYoutube = btnYoutube;
  }

  public abstract void setFragment(@Nullable ProfileFragment fragment);

  @Nullable
  public ProfileFragment getFragment() {
    return mFragment;
  }

  public abstract void setUser(@Nullable User user);

  @Nullable
  public User getUser() {
    return mUser;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentProfileBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_profile, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentProfileBinding>inflate(inflater, com.iindicar.indicar.R.layout.fragment_profile, null, false, component);
  }

  public static FragmentProfileBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentProfileBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentProfileBinding)bind(component, view, com.iindicar.indicar.R.layout.fragment_profile);
  }
}
