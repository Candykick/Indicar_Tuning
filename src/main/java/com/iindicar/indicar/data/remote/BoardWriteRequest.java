package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class BoardWriteRequest {

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("bbs_id") private String boardType;

    @SerializedName("ntcr_nm") private String userName;

    @SerializedName("ntcr_id") private String userId;

    @SerializedName("carSpecName") private String carName;

    @SerializedName("branch_id") private String branchId;

    @SerializedName("atch_file_id") private String fileIdArr;

    @SerializedName("ntt_sj") private String boardTitle;

    public BoardWriteRequest() {
    }

    public BoardWriteRequest(String boardId, String boardType, String userName, String userId, String carName, String branchId, String fileIdArr, String boardTitle) {
        this.boardId = boardId;
        this.boardType = boardType;
        this.userName = userName;
        this.userId = userId;
        this.carName = carName;
        this.branchId = branchId;
        this.fileIdArr = fileIdArr;
        this.boardTitle = boardTitle;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getFileIdArr() {
        return fileIdArr;
    }

    public void setFileIdArr(String fileIdArr) {
        this.fileIdArr = fileIdArr;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }
}
