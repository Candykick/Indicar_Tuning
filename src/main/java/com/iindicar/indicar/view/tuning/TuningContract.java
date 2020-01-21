package com.iindicar.indicar.view.tuning;

import com.iindicar.indicar.model.Car;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

import java.util.List;

public interface TuningContract {

    interface View extends BaseView<Presenter>{

        void changeTab(int index);

        void startUnityActivity(Car item);

        void onCarDataListLoaded(List<Car> list);
    }

    interface Presenter extends BasePresenter{

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void getSelectedCarList(int index);

        void loadItems();

        void onSearchCar(String searchKey);
    }
}
