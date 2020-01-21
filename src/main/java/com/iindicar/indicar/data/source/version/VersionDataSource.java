package com.iindicar.indicar.data.source.version;

import com.iindicar.indicar.model.Version;

public interface VersionDataSource {

    void getCurrentVersion(LoadDataCallback<Version> callback);

    interface LoadDataCallback<T> {

        void onDataLoaded(T item);

        void onDataNotAvailable(String error);
    }

}
