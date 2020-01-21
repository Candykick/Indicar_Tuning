package com.iindicar.indicar.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("item_id") private String ItemId;

    @SerializedName("item_name") private String productName;

    @SerializedName("spec_name") private String carSpecName;

    @SerializedName("atch_file_id") private String[] fileIdArr;

    @SerializedName("stre_file_nm") private String mainFileName;

    @SerializedName("file_stre_cours") private String mainFileUrl;

    @SerializedName("price_origin") private String productOriginalPrice;

    @SerializedName("is_discount") private Boolean isDiscount;

    @SerializedName("price_discount") private String productDiscountPrice;

    @SerializedName("is_best") private String isBest;

    @SerializedName("is_soldout") private String isSoldout;

    @SerializedName("is_new") private String isNew;

    @SerializedName("ref") private String webName;

    @SerializedName("attach_link") private String webUrl;

    @SerializedName("branch_id") private String branchId;

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCarSpecName() {
        return carSpecName;
    }

    public void setCarSpecName(String carSpecName) {
        this.carSpecName = carSpecName;
    }

    public String[] getFileIdArr() {
        return fileIdArr;
    }

    public void setFileIdArr(String[] fileIdArr) {
        this.fileIdArr = fileIdArr;
    }

    public String getMainFileName() {
        return mainFileName;
    }

    public void setMainFileName(String mainFileName) {
        this.mainFileName = mainFileName;
    }

    public String getMainFileUrl() {
        return mainFileUrl;
    }

    public void setMainFileUrl(String mainFileUrl) {
        this.mainFileUrl = mainFileUrl;
    }

    public String getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public void setProductOriginalPrice(String productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }

    public Boolean getDiscount() {
        return isDiscount;
    }

    public void setDiscount(Boolean discount) {
        isDiscount = discount;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getIsBest() {
        return isBest;
    }

    public void setIsBest(String isBest) {
        this.isBest = isBest;
    }

    public String getIsSoldout() {
        return isSoldout;
    }

    public void setIsSoldout(String isSoldout) {
        this.isSoldout = isSoldout;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
