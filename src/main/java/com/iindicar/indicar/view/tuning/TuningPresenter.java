package com.iindicar.indicar.view.tuning;

import android.annotation.SuppressLint;
import android.util.Log;

import com.iindicar.indicar.data.source.car.CarRepository;
import com.iindicar.indicar.model.Car;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class TuningPresenter implements TuningContract.Presenter, OnItemClickListener {

    private final String TAG = getClass().getSimpleName();

    private static final String[] CAR_LOGO_NAME_LIST = {
            "ALL",
            "HYUNDAI",
            "KIA",
            "VOLKSWAGEN",
            "CHEVROLET",
            "AUDI",
            "BMW",
            "BENZ"
    };

    private TuningContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Car> adapterModel;
    private CarRepository repository;

    private Map<String, List<Car>> carListMap = new HashMap<>(); // 자동차 리스트

    public TuningPresenter(TuningContract.View view, CarRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        this.adapterModel = model;
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadItems() {

        repository.loadDataList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    for(Car item : list){
                        String company = item.getCompany();

                        if(!carListMap.containsKey(company)){
                            carListMap.put(company, new ArrayList<>());
                        }
                        carListMap.get(company).add(item);
                    }
                    view.onCarDataListLoaded(list);
                }, error -> {});
    }

    @Override
    public void getSelectedCarList(int index){

        if(index == 0){
            // 전체 리스트
            adapterModel.clearItems();
            for (Map.Entry<String, List<Car>> entry : carListMap.entrySet()){
                adapterModel.addItems(entry.getValue());
            }
        } else if(carListMap.containsKey(CAR_LOGO_NAME_LIST[index])) {
            // 회사별 리스트
            adapterModel.updateItems(carListMap.get(CAR_LOGO_NAME_LIST[index]));
        } else {
            // 차량 데이터 없음
            adapterModel.clearItems();
        }
    }

    @Override
    public void onSearchCar(String searchKey){
        ((TuningAdapter)adapterModel).getFilter().filter(searchKey);
    }

    /**
     * 차량 아이템 클릭 콜백 */
    @Override
    public void onItemClick(int position) {

        view.startUnityActivity(adapterModel.getItem(position));
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }
}
