package com.iindicar.indicar.data.source;

import io.reactivex.Observable;

public interface DeleteData<S> {

    Observable<String> deleteData(S source);

}
