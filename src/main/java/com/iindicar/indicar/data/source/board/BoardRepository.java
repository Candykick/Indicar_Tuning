package com.iindicar.indicar.data.source.board;

import com.iindicar.indicar.data.remote.BoardDeleteRequest;
import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.BoardWriteRequest;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardWrite;
import java.util.List;

import io.reactivex.Observable;

/**
 * 1. 게시물 리스트 조회
 * 2. 게시글 상세 조회
 * 3. 게시물 등록 / 수정 / 삭제
 *
 * 게시물 등록의 경우 글쓰기 창이 닫힐 때까지 작성한 데이터를 이곳에 캐싱한다
 *
 * 게시물 상세 조회할 때마다 좋아요 여부 필요하므로 좋아요 한 게시물 리스트 캐싱
 * 좋아요 버튼 누를때만 dirty flag ON 시켜서 요청 보냄 */
public class BoardRepository implements BoardDataSource {

    public static final int DATA_TYPE_POP = 0;
    public static final int DATA_TYPE_ALL = 1;
    public static final int DATA_TYPE_LIKE = 2;
    public static final int DATA_TYPE_MY_BOARD = 3;
    public static final int DATA_TYPE_MY_COMMENT = 4;

    private static BoardRepository boardRepository;

    private BoardRemoteDataSource remoteDataSource;

    private BoardWrite cacheWriteBoard;

    private List<Board> cacheLikeBoards;
    private boolean isCacheLikeDirty = true;

    private BoardRepository() {
        remoteDataSource = BoardRemoteDataSource.getInstance();
    }

    public static BoardRepository getInstance(){
        if(boardRepository == null){
            boardRepository = new BoardRepository();
        }
        return boardRepository;
    }

    @Override
    public Observable<String> insertData(BoardWriteRequest source) {
        return remoteDataSource.insertData(source);
    }

    @Override
    public Observable<String> updateData(BoardWriteRequest source) {
        return remoteDataSource.updateData(source);
    }

    @Override
    public Observable<String> deleteData(BoardDeleteRequest source) {
        return remoteDataSource.deleteData(source);
    }

    @Override
    public Observable<Board> loadData(BoardDetailRequest source) {
        return remoteDataSource.loadData(source);
    }

    @Override
    public Observable<List<Board>> loadDataList(BoardListRequest source) {
        return remoteDataSource.loadDataList(source);
    }

    @Override
    public BoardWrite getCacheWriteBoard() {
        return cacheWriteBoard;
    }

    @Override
    public void setCacheWriteBoard(BoardWrite cacheWriteBoard) {
        this.cacheWriteBoard = cacheWriteBoard;
    }

    @Override
    public Observable<List<Board>> loadDataList(int dataType, BoardListRequest source) {
        if(dataType == DATA_TYPE_LIKE){
            // 좋아요 목록의 경우 캐시 처리 필요함
            if(isCacheLikeDirty) {
                // 목록을 새로 요청할 경우
                return remoteDataSource.loadDataList(dataType, source)
                        .doOnNext(list -> {
                            cacheLikeBoards = list;
                            isCacheLikeDirty = false;
                        });

            } else {
                // 캐시에 있는 경우 캐시 리턴
                return Observable.just(cacheLikeBoards);
            }
        }
        return remoteDataSource.loadDataList(dataType, source);
    }

    @Override
    public Observable<String> setBoardLike(BoardDetailRequest source) {
        return remoteDataSource.setBoardLike(source);
    }

    public void setCacheLikeDirty(boolean cacheLikeDirty) {
        this.isCacheLikeDirty = cacheLikeDirty;
    }
}
