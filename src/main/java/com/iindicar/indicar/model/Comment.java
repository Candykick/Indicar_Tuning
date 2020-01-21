package com.iindicar.indicar.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id") private Integer id;

    @SerializedName("comment_cn") private String text;

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("depth") private String commentDepth;

    @SerializedName("parent") private Integer parentId;

    @SerializedName("frst_time") private String firstTime;

    @SerializedName("last_updt_time") private String lastUpdateTime;

    @SerializedName("writer_id") private String userId;

    @SerializedName("name") private String userName;

    @SerializedName("profile_img_url") private String userImageUrl;

    public String getCommentDepth() {
        return commentDepth;
    }

    public void setCommentDepth(String commentDepth) {
        this.commentDepth = commentDepth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
