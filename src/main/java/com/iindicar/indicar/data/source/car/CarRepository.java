package com.iindicar.indicar.data.source.car;

import com.iindicar.indicar.model.Car;

import java.util.List;

import io.reactivex.Observable;

public class CarRepository implements CarDataSource {

    private static CarRepository carRepository;

    private CarRemoteDataSource remoteDataSource;

    private CarRepository() {
        remoteDataSource = CarRemoteDataSource.getInstance();
    }

    public static CarRepository getInstance(){
        if(carRepository == null){
            carRepository = new CarRepository();
        }
        return carRepository;
    }

    @Override
    public Observable<List<Car>> loadDataList() {
        return remoteDataSource.loadDataList();
    }
}
