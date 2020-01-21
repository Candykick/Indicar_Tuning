package com.iindicar.indicar.view.community.boardList;

import android.view.View;

import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface BoardListContract {

    interface View extends BaseView<Presenter> {

        void startBoardDetailActivity(Board board);

        void onBoardListLoaded(int count);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems(boolean isRefresh);

        void loadItemsChanged();
    }


    interface SearchView extends BaseView<Presenter> {

        void startBoardDetailActivity(Board board);

        void setRefreshing(boolean refreshing);

        void onLoadBoardsFail();
    }

    interface SearchPresenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems(boolean isRefresh);

        void onSubmitButtonClicked();

        void onBoardFilterClicked(android.view.View view);
    }
}
