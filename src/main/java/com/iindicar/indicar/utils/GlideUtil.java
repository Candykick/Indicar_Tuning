package com.iindicar.indicar.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.iindicar.indicar.R;

public class GlideUtil {

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String url){

        if (url == null) return;

        GlideApp.with(imageView).load(url).into(imageView);
    }

    @BindingAdapter({"android:srcFuture"})
    public static void loadFutureImageDrawable(ImageView imageView, Drawable drawable){

        GlideApp.with(imageView).load(drawable).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                int width = imageView.getMeasuredWidth();
                int targetHeight = width * resource.getIntrinsicHeight() / resource.getIntrinsicWidth();
                if(imageView.getLayoutParams().height != targetHeight){
                    imageView.getLayoutParams().height = targetHeight;
                    imageView.requestLayout();
                }
                GlideApp.with(imageView).load(drawable).override(width, targetHeight).fitCenter().into(imageView);
            }
        });
    }

    @BindingAdapter({"image"})
    public static void loadImageResource(ImageView imageView, int resourceId){

        GlideApp.with(imageView).load(resourceId).into(imageView);
    }

    /**
     * 게시판 목록, 앨범 목록에서 사용 */
    @BindingAdapter({"imageCenterCrop"})
    public static void loadImageCenterCrop(ImageView imageView, String url){

        if (url == null) return;

        GlideApp.with(imageView).load(url).centerCrop().into(imageView);
    }

    /**
     * 프로필 이미지에서 사용 */
    @BindingAdapter({"imageCircle"})
    public static void loadCircleImage(ImageView imageView, String url) {

        if (url == null) return;

        GlideApp.with(imageView).load(url).circleCrop().placeholder(R.drawable.icon_user).into(imageView);
    }

    public static void clear(View view){

        GlideApp.with(view).clear(view);
    }

    public static void loadGif(ImageView imageView, int tutorial1) {
        GlideApp.with(imageView).asGif().load(tutorial1).into(imageView);
    }
}
