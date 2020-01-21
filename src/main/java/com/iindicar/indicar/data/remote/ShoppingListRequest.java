package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class ShoppingListRequest {

    @SerializedName("pageIndex") private int pageIndex;

    @SerializedName("pageUnit") private int pageUnit;

    @SerializedName("branch_id") private String branchId;

    public ShoppingListRequest() {}

    public ShoppingListRequest(int pageIndex, int pageUnit, String branchId) {
        this.pageIndex = pageIndex;
        this.pageUnit = pageUnit;
        this.branchId = branchId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
