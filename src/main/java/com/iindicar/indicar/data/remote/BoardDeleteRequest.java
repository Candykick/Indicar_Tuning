package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class BoardDeleteRequest {

    @SerializedName("bbs_id") private String boardType;

    @SerializedName("ntt_id") private String boardId;

    public BoardDeleteRequest() {
    }

    public BoardDeleteRequest(String boardType, String boardId) {
        this.boardType = boardType;
        this.boardId = boardId;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
