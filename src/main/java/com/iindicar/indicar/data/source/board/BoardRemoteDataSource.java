package com.iindicar.indicar.data.source.board;

import android.annotation.SuppressLint;

import com.iindicar.indicar.data.remote.BoardDeleteRequest;
import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.BoardWriteRequest;
import com.iindicar.indicar.data.remote.LoadDataListResponse;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.mapper.BoardMapper;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;
import com.iindicar.indicar.model.Comment;
import com.iindicar.indicar.view.community.boardList.BoardListFragment;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class BoardRemoteDataSource implements BoardDataSource {

    private static BoardRemoteDataSource boardRemoteDataSource;

    public BoardRemoteDataSource(){}

    public static BoardRemoteDataSource getInstance(){
        if(boardRemoteDataSource == null){
            boardRemoteDataSource = new BoardRemoteDataSource();
        }
        return boardRemoteDataSource;
    }

    /**
     * 각 요청에 해당하는 게시물 리스트 조회 api 요청 후 Observable 로 전달
     * mainFileUrl 앞에 http:// 붙어있는지 체크 필요함 */
    @SuppressLint("CheckResult")
    @Override
    public Observable<List<Board>> loadDataList(int DATA_TYPE, BoardListRequest source) {

        Observable<LoadDataListResponse<Board>> result;

        switch (DATA_TYPE){
            case BoardListFragment.BOARD_LIST_STAR: default:
                // 추천 게시물 리스트 요청
                result = RetrofitClient.getClient().create(RetrofitApi.class).getBoards(source);
                break;
            case BoardListFragment.BOARD_LIST_ALL:
                // 전체 게시물 리스트 요청
                result = RetrofitClient.getClient().create(RetrofitApi.class).getBoards(source);
                break;
            case BoardListFragment.BOARD_LIST_LIKE:
                // 좋아요 게시물 리스트 요청
                result = RetrofitClient.getClient().create(RetrofitApi.class).getLikeBoards(source);
                break;
            case BoardListFragment.BOARD_LIST_MY_BOARD:
                // 내가 쓴 게시물 리스트 요청
                result = RetrofitClient.getClient().create(RetrofitApi.class).getMyBoards(source);
                break;
            case BoardListFragment.BOARD_LIST_MY_COMMENT:
                // 내 댓글 게시물 리스트 요청
                result = RetrofitClient.getClient().create(RetrofitApi.class).getMyCommentBoards(source);
                break;
        }

        return result.subscribeOn(Schedulers.newThread())
                .map(response -> {
                    List<Board> boardList = response.getContent();
                    for(Board board : boardList){
                        String url = BoardMapper.checkImageUrl(board.getMainFileUrl());
                        board.setMainFileUrl(url);
                    }
                    return boardList;
                });
    }

    /**
     * 좋아요 요청 후 Observable 로 전달 */
    @Override
    public Observable<String> setBoardLike(BoardDetailRequest source) {

        // 좋아요 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .setBoardLike(source)
                .subscribeOn(Schedulers.newThread())
                .map(LoadDataResponse::getResult);
    }

    /**
     * 게시글 리스트 조회 api 요청 후 Observable 로 전달
     * mainFileUrl 앞에 http:// 붙어있는지 체크 필요함 */
    @Override
    public Observable<List<Board>> loadDataList(BoardListRequest source) {

        // 게시글 리스트 조회 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getBoards(source)
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                    List<Board> boardList = response.getContent();
                    for(Board board : boardList){
                        String url = BoardMapper.checkImageUrl(board.getMainFileUrl());
                        board.setMainFileUrl(url);
                    }
                    return boardList;
                });
    }

    /**
     * 게시글 상세 조회 api 요청 후 Observable 로 전달
     * 결과 리스트 (0) Board  (1) File List  (2) Comment List  (이유는 api 돌려보면 알 수 있음)
     * fileList 각각 URL 앞에 http:// 붙어있는지 체크 필요함 */
    @Override
    public Observable<Board> loadData(BoardDetailRequest source) {

        // 게시물 상세 조회 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getBoard(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                    // 결과 받아서 필요한 연산 수행
                    List<Board> list = response.getContent();
                    // response body 에서 file 리스트를 가져옴
                    List<BoardFile> fileList = list.get(1).getFileList();
                    for(BoardFile file : fileList){
                        // url 앞에 "http://" 붙어있는지 체크
                        String url = BoardMapper.checkImageUrl(file.getFilePath());
                        file.setFilePath(url);
                    }
                    // 리스트의 첫번째 객체가 결과
                    Board result = list.get(0);
                    // file 리스트 저장
                    result.setFileList(fileList);
                    // comment 리스트 가져와서 저장
                    List<Comment> commentList = list.get(2).getCommentList();
                    Collections.reverse(commentList);
                    result.setCommentList(commentList);

                    return result;
                });
    }

    /**
     * 게시글 업로드 api 요청 후 Result String 만 뽑아서 Observable 로 전달 */
    @Override
    public Observable<String> insertData(BoardWriteRequest source) {

        // 게시글 업로드 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .insertBoard(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // 결과 중에 result string 만 반환
                .map(LoadDataResponse::getResult);
    }

    /**
     * 게시글 수정 api 요청 후 Result String 만 뽑아서 Observable 로 전달 */
    @Override
    public Observable<String> updateData(BoardWriteRequest source) {

        // 게시글 수정 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .updateBoard(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // 결과 중에 result string 만 반환
                .map(LoadDataResponse::getResult);
    }

    /**
     * 게시글 삭제 api 요청 후 Result String 만 뽑아서 Observable 로 전달 */
    @Override
    public Observable<String> deleteData(BoardDeleteRequest source) {

        // 게시글 삭제 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .deleteBoard(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // 결과 중에 result string 만 반환
                .map(LoadDataResponse::getResult);
    }

    @Override
    public BoardWrite getCacheWriteBoard() {
        return null;
    }

    @Override
    public void setCacheWriteBoard(BoardWrite cacheWriteBoard) {
        // do nothing
    }


}
