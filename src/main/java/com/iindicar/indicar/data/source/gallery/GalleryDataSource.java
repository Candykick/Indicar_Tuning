package com.iindicar.indicar.data.source.gallery;

import java.util.List;

public interface GalleryDataSource<T> {

    void getImages(LoadImageListCallback<T> callback);

    interface LoadImageListCallback<T> {

        void onImageListLoaded(List<T> items);

        void onDataNotAvailable();
    }

}
