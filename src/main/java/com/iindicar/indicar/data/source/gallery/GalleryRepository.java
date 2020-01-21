package com.iindicar.indicar.data.source.gallery;

import android.content.Context;

import com.iindicar.indicar.data.local.GalleryVO;

import java.util.List;

public class GalleryRepository implements GalleryDataSource<GalleryVO> {

    private static GalleryRepository galleryRepository;

    private GalleryLocalDataSource localDataSource;

    private GalleryRepository(Context context){
        this.localDataSource = GalleryLocalDataSource.getInstance(context);
    }

    public static GalleryRepository getInstance(Context context){
        if(galleryRepository == null){
            galleryRepository = new GalleryRepository(context);
        }
        return galleryRepository;
    }

    @Override
    public void getImages(LoadImageListCallback<GalleryVO> callback) {

        localDataSource.getImages(new LoadImageListCallback<GalleryVO>() {
            @Override
            public void onImageListLoaded(List<GalleryVO> items) {
                if(items.size() != 0) {
                    callback.onImageListLoaded(items);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

}
