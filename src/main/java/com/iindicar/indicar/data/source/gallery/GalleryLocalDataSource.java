package com.iindicar.indicar.data.source.gallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.iindicar.indicar.data.local.GalleryVO;

import java.util.ArrayList;
import java.util.List;

public class GalleryLocalDataSource implements GalleryDataSource<GalleryVO> {

    private static GalleryLocalDataSource galleryLocalDataSource;
    private Context context;

    private GalleryLocalDataSource(Context context){
        this.context = context;
    }

    public static GalleryLocalDataSource getInstance(Context context){
        if(galleryLocalDataSource == null){
            galleryLocalDataSource = new GalleryLocalDataSource(context);
        }
        return galleryLocalDataSource;
    }

    @Override
    public void getImages(LoadImageListCallback<GalleryVO> callback) {

        // 사진 정보(ID, path data) Media DB 에서 가져옴 (최신순)
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] columns = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA};
        String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";

        Cursor cursor = context.getContentResolver().query(uri, columns, null, null, orderBy);

        if(cursor == null || !cursor.moveToFirst()){
            return;
        }

        List<GalleryVO> galleryList = new ArrayList<>();

        int imageIdIndex = cursor.getColumnIndex(columns[0]);
        int imageDataIndex = cursor.getColumnIndex(columns[1]);
        do{
            GalleryVO vo = new GalleryVO(
                    cursor.getString(imageIdIndex),
                    cursor.getString(imageDataIndex));
            galleryList.add(vo);
        } while (cursor.moveToNext());

        callback.onImageListLoaded(galleryList);
    }

}
