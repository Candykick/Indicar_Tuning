package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class UserUpdateRequest {

    @SerializedName("id") private String userId;

    @SerializedName("name") private String userName;

    public UserUpdateRequest(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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
}
