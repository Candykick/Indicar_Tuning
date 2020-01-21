package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class LoadDataResponse<T> {

    @SerializedName("result") private String result;

    @SerializedName("content") private T content;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
