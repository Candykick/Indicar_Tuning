package com.iindicar.indicar.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notice {

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("bbs_id") private String boardType;

    @SerializedName("ntcr_id") private String authorId;

    @SerializedName("file_stre_cours") private String mainFileUrl;

    @SerializedName("file_cn") private String mainFileContent;

    @SerializedName("frst_time") private String writeTime;

    @SerializedName("ntt_sj") private String noticeTitle;

    @SerializedName("ntt_cn") private String noticeContent;

    @SerializedName("FILEDETAIL") private List<BoardFile> fileList;

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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getMainFileUrl() {
        return mainFileUrl;
    }

    public void setMainFileUrl(String mainFileUrl) {
        this.mainFileUrl = mainFileUrl;
    }

    public String getMainFileContent() {
        return mainFileContent;
    }

    public void setMainFileContent(String mainFileContent) {
        this.mainFileContent = mainFileContent;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public List<BoardFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<BoardFile> fileList) {
        this.fileList = fileList;
    }
}
