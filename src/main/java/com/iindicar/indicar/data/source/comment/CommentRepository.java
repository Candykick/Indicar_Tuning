package com.iindicar.indicar.data.source.comment;

import com.iindicar.indicar.data.remote.CommentListRequest;
import com.iindicar.indicar.data.remote.CommentWriteRequest;
import com.iindicar.indicar.model.Comment;

import java.util.List;

import io.reactivex.Observable;

/**
 * 1. 댓글 목록 데이터 제공
 * 2. 댓글 등록 / 수정 / 삭제 요청 담당
 *
 * */
public class CommentRepository implements CommentDataSource {

    private static CommentRepository commentRepository;

    private CommentRemoteDataSource remoteDataSource;

    private CommentRepository(){

        remoteDataSource = CommentRemoteDataSource.getInstance();
    }

    public static CommentRepository getInstance(){

        if(commentRepository == null){
            commentRepository = new CommentRepository();
        }
        return commentRepository;
    }

    /**
     * remote 에 댓글 목록을 요청 */
    @Override
    public Observable<List<Comment>> loadDataList(CommentListRequest source) {
        return remoteDataSource.loadDataList(source);
    }

    /**
     * remote 에 해당 댓글 삭제 요청 */
    @Override
    public Observable<String> deleteData(CommentWriteRequest source) {

        // remote 에 데이터 삭제 요청 후 dirty flag ON
        return remoteDataSource.deleteData(source);
    }

    /**
     * remote 에 댓글 등록 요청 */
    @Override
    public Observable<String> insertData(CommentWriteRequest source) {

        // remote 서버에 데이터 생성 요청 후 dirty flag ON
        return remoteDataSource.insertData(source);
    }

    /**
     * remote 에 댓글 수정 요청 */
    @Override
    public Observable<String> updateData(CommentWriteRequest source) {

        // remote 에 데이터 수정 요청 후 dirty flag ON
        return remoteDataSource.updateData(source);
    }

}
