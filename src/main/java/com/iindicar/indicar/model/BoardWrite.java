package com.iindicar.indicar.model;

import java.util.ArrayList;
import java.util.List;

public class BoardWrite {

    private String boardId;

    private String boardType;

    private List<BoardFile> fileList = new ArrayList<>();

    public BoardWrite() {

    }

    public BoardWrite(String boardId, String boardType, List<BoardFile> fileList) {
        this.boardId = boardId;
        this.boardType = boardType;
        this.fileList = fileList;
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

    public List<BoardFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<BoardFile> fileList) {
        this.fileList.clear();
        this.fileList.addAll(fileList);
    }
}
