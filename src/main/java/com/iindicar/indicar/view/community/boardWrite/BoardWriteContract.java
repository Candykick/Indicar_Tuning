package com.iindicar.indicar.view.community.boardWrite;

import android.content.Intent;

import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface BoardWriteContract {

    interface FilterView extends BaseView<FilterPresenter> {

        void onStartAlbumActivity();

        void makeToast(String text);

        void onStartContentFragment();

        void onStartCameraActivity();
    }

    interface FilterPresenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems();

        void onActivityResult(int requestCode, Intent data);

        void onNextButtonClicked();
    }


    interface ContentView extends BaseView<ContentPresenter> {

        void makeToast(String result);
    }

    interface ContentPresenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems();
    }
}
