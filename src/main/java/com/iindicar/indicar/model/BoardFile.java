package com.iindicar.indicar.model;

import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class BoardFile extends BaseObservable {

    public static final int FLAG_NULL = 0;
    public static final int FLAG_INSERT = 1;
    public static final int FLAG_UPDATE = 2;

    @SerializedName("atch_file_id") private String fileId = "";

    @SerializedName("file_cn") private String fileText = "";

    @SerializedName("file_stre_cours") private String filePath;

    private int uploadFlag = FLAG_NULL;

    public BoardFile() {
    }

    public BoardFile(String fileId, String fileText, String filePath, int uploadFlag) {
        this.fileId = fileId;
        this.fileText = fileText;
        this.filePath = filePath;
        this.uploadFlag = uploadFlag;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileText() {
        return fileText;
    }

    public void setFileText(String fileText) {
        this.fileText = fileText;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(int uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

}
