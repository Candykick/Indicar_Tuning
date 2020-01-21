package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class UserBoardListRequest {

    @SerializedName("bbs_id") private String boardType;

    @SerializedName("searchCnd") private String searchCnd;

    @SerializedName("pageIndex") private int pageIndex;

    @SerializedName("pageUnit") private int pageUnit;

    @SerializedName("branch_id") private String branchId;

    public UserBoardListRequest() {
    }

    public UserBoardListRequest(String boardType, String searchCnd, int pageIndex, int pageUnit, String branchId) {
        this.boardType = boardType;
        this.searchCnd = searchCnd;
        this.pageIndex = pageIndex;
        this.pageUnit = pageUnit;
        this.branchId = branchId;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getSearchCnd() {
        return searchCnd;
    }

    public void setSearchCnd(String searchCnd) {
        this.searchCnd = searchCnd;
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
