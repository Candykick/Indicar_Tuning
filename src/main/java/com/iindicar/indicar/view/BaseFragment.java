package com.iindicar.indicar.view;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iindicar.indicar.utils.LocaleHelper;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {

    protected final String TAG = getClass().getSimpleName();

    protected B binding;
    protected Context context;
    protected Resources resources;

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        context = binding.getRoot().getContext();

        resources = getResources();

        return binding.getRoot();
    }

    public void makeToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}