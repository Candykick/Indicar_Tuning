package com.iindicar.indicar.view.shopping.shoppingList;

import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.community.boardList.BoardListContract;

public interface ProductListContract {

    interface View extends BaseView<BoardListContract.Presenter> {

        void startShoppingDetailActivity(Product product);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems(boolean isRefresh);
    }

}
