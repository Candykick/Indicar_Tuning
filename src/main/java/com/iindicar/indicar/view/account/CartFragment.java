package com.iindicar.indicar.view.account;

import com.iindicar.indicar.R;
import com.iindicar.indicar.view.BaseFragment;

public class CartFragment extends BaseFragment {


    public CartFragment(){}

    public static CartFragment newInstance(){
        return new CartFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

}
