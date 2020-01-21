package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class ShoppingDetailRequest {

    @SerializedName("item_id") private String itemId;

    @SerializedName("branch_id") private String branchId;

    public ShoppingDetailRequest() {}

    public ShoppingDetailRequest(String itemId, String branchId) {
        this.itemId = itemId;
        this.branchId = branchId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
