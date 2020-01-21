package com.iindicar.indicar.view.shopping;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.FragmentShoppingEngBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.listener.OnMenuClickListener;

public class ShoppingEngFragment extends BaseFragment<FragmentShoppingEngBinding> {

    private OnMenuClickListener drawerButtonClickListener;

    public ShoppingEngFragment() {
        // Required empty public constructor
    }

    public static ShoppingEngFragment newInstance() {
        return new ShoppingEngFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMenuClickListener){
            drawerButtonClickListener = (OnMenuClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFilterFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        drawerButtonClickListener = null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_eng;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_shopping);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_menu);
        binding.toolbar.btnLeft.setOnClickListener(v -> drawerButtonClickListener.onDrawerButtonClicked());
    }
}
