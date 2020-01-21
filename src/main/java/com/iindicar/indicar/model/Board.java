package com.iindicar.indicar.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.iindicar.indicar.BR;

import java.util.List;

public class Board extends BaseObservable {

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("bbs_id") private String boardType;

    @SerializedName("like") private Integer likeCount;

    @SerializedName("branch_id") private String branchId;

    @SerializedName("spec_name") private String carName;

    @SerializedName("frst_time") private String writeTime;

    @SerializedName("CntCOMMENT") private int commentCount;

    @SerializedName("atch_file_id") private String[] fileIdArr;

    @SerializedName("stre_file_nm") private String mainFileName;

    @SerializedName("file_stre_cours") private String mainFileUrl;

    @SerializedName("file_cn") private String mainText;

    @SerializedName("profile_img_url") private String authorImageUrl;

    @SerializedName("name") private String authorName;

    @SerializedName("ntcr_id") private String authorId;

    @SerializedName("FILEDETAIL") private List<BoardFile> fileList;

    @SerializedName("COMMENT") private List<Comment> commentList;

    private boolean isLikeBoard;

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

    @Bindable
    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        notifyPropertyChanged(BR.likeCount);
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    @Bindable
    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        notifyPropertyChanged(BR.commentCount);
    }

    public String[] getFileIdArr() {
        return fileIdArr;
    }

    public void setFileIdArr(String[] fileIdArr) {
        this.fileIdArr = fileIdArr;
    }

    public String getMainFileName() {
        return mainFileName;
    }

    public void setMainFileName(String mainFileName) {
        this.mainFileName = mainFileName;
    }

    public String getMainFileUrl() {
        return mainFileUrl;
    }

    public void setMainFileUrl(String mainFileUrl) {
        this.mainFileUrl = mainFileUrl;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getAuthorImageUrl() {
        return authorImageUrl;
    }

    public void setAuthorImageUrl(String authorImageUrl) {
        this.authorImageUrl = authorImageUrl;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<BoardFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<BoardFile> fileList) {
        this.fileList = fileList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Bindable
    public boolean isLikeBoard() {
        return isLikeBoard;
    }

    public void setLikeBoard(boolean likeBoard) {
        isLikeBoard = likeBoard;
        notifyPropertyChanged(BR.likeBoard);
    }
}
