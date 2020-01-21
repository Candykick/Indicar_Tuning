package com.iindicar.indicar.view.album;

import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface AlbumContract {

    interface View extends BaseView<Presenter> {

        void setImagesCount(int size);

        void finishActivityWithResult(String[] pathArr);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems();

        void onDoneButtonClicked();
    }
}
