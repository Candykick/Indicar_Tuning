package com.iindicar.indicar.data.source;


import io.reactivex.Observable;

public interface LoadData<S, T> {

    Observable<T> loadData(S source);

}
