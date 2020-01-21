package com.iindicar.indicar.data.local;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.iindicar.indicar.BR;

public class GalleryVO extends BaseObservable {
    private String mediaId;
    private String mediaPath;
    private boolean selected;
    private String index;

    public GalleryVO(String mediaId, String mediaPath) {
        this.mediaId = mediaId;
        this.mediaPath = mediaPath;
        this.selected = false;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    @Bindable
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
        notifyPropertyChanged(BR.mediaId);
    }

    @Bindable
    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
        notifyPropertyChanged(BR.mediaPath);
    }

    @Bindable
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
        notifyPropertyChanged(BR.index);
    }
}
