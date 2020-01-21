package com.iindicar.indicar.data.source.car;

import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.mapper.BoardMapper;
import com.iindicar.indicar.model.Car;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CarRemoteDataSource implements CarDataSource {

    private static CarRemoteDataSource carRemoteDataSource;

    private CarRemoteDataSource() {}

    public static CarRemoteDataSource getInstance() {
        if(carRemoteDataSource == null){
            carRemoteDataSource = new CarRemoteDataSource();
        }
        return carRemoteDataSource;
    }

    @Override
    public Observable<List<Car>> loadDataList() {
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getCars()
                .subscribeOn(Schedulers.newThread())
                .map(carList -> {
                    for(Car car : carList){
                        String url = BoardMapper.checkImageUrl(car.getCarImageUrl());
                        car.setCarImageUrl(url);
                    }
                    return carList;
                });
    }
}
