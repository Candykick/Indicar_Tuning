package com.iindicar.indicar.model;

public class BoardFilterItem {

    private String typeName;
    private int imageId;

    public BoardFilterItem(String typeName, int imageId) {
        this.typeName = typeName;
        this.imageId = imageId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
