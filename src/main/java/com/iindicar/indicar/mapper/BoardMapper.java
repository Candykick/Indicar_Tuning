package com.iindicar.indicar.mapper;

import android.util.Log;

import com.iindicar.indicar.R;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시판에서 필요한 변환 연산 수행 */
public class BoardMapper {

    private static final String TAG = "BoardMapper";

    /**
     * 필터 이름 -> String resource ID 로 변환
     * BoardDetail 에서 게시판 필터 이름 띄울때 사용함 */
    public static int filterNameToStringResourceId(String boardType){
        if("daylife".equals(boardType)){
            return R.string.filter_daylife;
        } else if("market".equals(boardType)){
            return R.string.filter_market;
        } else if("diy".equals(boardType)){
            return R.string.filter_diy;
        }
        return 0;
    }

    /**
     * 게시물 수정 페이지 넘어갈때 Board -> BoardWrite 객체로 변환 */
    public static BoardWrite boardToBoardWrite(Board board){

        return new BoardWrite(board.getBoardId(), board.getBoardType(), board.getFileList());
    }

    /**
     * 서버에서 온 이미지 URL 맨 앞에 http 안 붙은 경우 예외 처리 */
    public static String checkImageUrl(String url){

        StringBuilder sb = new StringBuilder(url);
        if(url.contains("13.125.173.118:9000") && !url.startsWith("http://")) {
            url = "http://" + url;
        }

        return url;
    }
}
