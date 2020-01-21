package com.iindicar.indicar.data.source.car;

import com.iindicar.indicar.model.Car;

import java.util.List;

import io.reactivex.Observable;

public interface CarDataSource {

    Observable<List<Car>> loadDataList();
}
