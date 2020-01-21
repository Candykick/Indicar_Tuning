package com.iindicar.indicar.view.shopping.shoppingHome;

import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.community.boardList.BoardListContract;

public interface ShoppingHomeContract {

    interface View extends BaseView<BoardListContract.Presenter> {

        void startShoppingDetailActivity(Product product);
    }

    interface Presenter extends BasePresenter {

        void setBestAdapterView(AdapterContract.View view);

        void setBestAdapterModel(AdapterContract.Model model);

        void setNewAdapterView(AdapterContract.View view);

        void setNewAdapterModel(AdapterContract.Model model);

        void loadItems(boolean isRefresh);
    }

}
