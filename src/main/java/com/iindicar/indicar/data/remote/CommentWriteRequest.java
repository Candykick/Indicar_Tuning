package com.iindicar.indicar.data.remote;

import com.google.gson.annotations.SerializedName;

public class CommentWriteRequest {

    @SerializedName("ntt_id") private String boardId;

    @SerializedName("comment_cn") private String text;

    @SerializedName("writer_id") private String userId;

    @SerializedName("id") private Integer commentId;

    @SerializedName("parent") private Integer parentId;

    public CommentWriteRequest() {
    }

    public CommentWriteRequest(String boardId, String text, String userId, Integer commentId) {
        this.boardId = boardId;
        this.text = text;
        this.userId = userId;
        this.commentId = commentId;
    }

    public CommentWriteRequest(String boardId, String text, String userId, Integer commentId, Integer parentId) {
        this.boardId = boardId;
        this.text = text;
        this.userId = userId;
        this.commentId = commentId;
        this.parentId = parentId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
