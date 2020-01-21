package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoadDataListResponse<T> {

    @SerializedName("result") private String result;

    @SerializedName("content") private List<T> content;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
