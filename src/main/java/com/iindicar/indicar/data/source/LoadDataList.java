package com.iindicar.indicar.data.source;

import java.util.List;

import io.reactivex.Observable;

public interface LoadDataList<S, T> {

    Observable<List<T>> loadDataList(S source);

}
