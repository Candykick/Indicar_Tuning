package com.iindicar.indicar.view.shopping.shoppingDetail;

import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;

public interface ShoppingDetailContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void loadItems();

        void onShareButtonClicked();

        void onLikeButtonClicked();
    }
}
