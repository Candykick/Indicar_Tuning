package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class CommentListRequest {

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("pageIndex") private int pageIndex;

    @SerializedName("pageUnit") private int pageUnit;

    public CommentListRequest() {
    }

    public CommentListRequest(String boardId, int pageIndex, int pageUnit) {

        this.boardId = boardId;
        this.pageIndex = pageIndex;
        this.pageUnit = pageUnit;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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
}
