package com.iindicar.indicar.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id") private String userId;

    @SerializedName("name") private String userName;

    @SerializedName("email") private String userEmail;

    @SerializedName("login_method") private String userLoginMethod;

    @SerializedName("profile_img_url") private String userImageUrl;

    @SerializedName("address") private String userAddress;

    private int likeCount;

    private int boardCount;

    public User() { }

    public User(String userId, String userName, String userEmail, String userLoginMethod, String userImageUrl, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLoginMethod = userLoginMethod;
        this.userImageUrl = userImageUrl;
        this.userAddress = userAddress;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLoginMethod() {
        return userLoginMethod;
    }

    public void setUserLoginMethod(String userLoginMethod) {
        this.userLoginMethod = userLoginMethod;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getBoardCount() {
        return boardCount;
    }

    public void setBoardCount(int boardCount) {
        this.boardCount = boardCount;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
