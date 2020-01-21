package com.iindicar.indicar.data.source;

import io.reactivex.Observable;

public interface UpdateData<S> {

    Observable<String> updateData(S source);
}
