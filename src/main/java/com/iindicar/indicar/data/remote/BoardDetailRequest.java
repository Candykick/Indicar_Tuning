package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class BoardDetailRequest {

    @SerializedName("id") private String userId;

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("bbs_id") private String boardType;

    @SerializedName("branch_id") private String branchId;

    public BoardDetailRequest() {
    }

    public BoardDetailRequest(String boardId, String boardType, String branchId) {
        this.boardId = boardId;
        this.boardType = boardType;
        this.branchId = branchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
