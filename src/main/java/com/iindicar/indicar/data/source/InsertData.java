package com.iindicar.indicar.data.source;

import io.reactivex.Observable;

public interface InsertData<S> {

    Observable<String> insertData(S source);

}
