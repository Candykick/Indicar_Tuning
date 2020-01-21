package com.iindicar.indicar.data.source.comment;

import com.iindicar.indicar.data.remote.CommentListRequest;
import com.iindicar.indicar.data.remote.CommentWriteRequest;
import com.iindicar.indicar.data.remote.LoadDataListResponse;
import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.model.Comment;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CommentRemoteDataSource implements CommentDataSource {

    private static CommentRemoteDataSource commentRemoteDataSource;

    private CommentRemoteDataSource(){ }

    public static CommentRemoteDataSource getInstance(){

        if(commentRemoteDataSource == null){
            commentRemoteDataSource = new CommentRemoteDataSource();
        }

        return commentRemoteDataSource;
    }

    /**
     * 댓글 리스트 조회 api 요청 후 Observable 전달 */
    @Override
    public Observable<List<Comment>> loadDataList(CommentListRequest source) {

        // 댓글 등록 api 요청하고 Observable 반환
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getComments(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // response 중에 댓글 리스트(Content) 만 얻기 위해 map() 함수 사용
                .map(LoadDataListResponse::getContent);
    }

    /**
     * 댓글 등록 api 요청 후 Observable 로 전달 */
    @Override
    public Observable<String> insertData(CommentWriteRequest source) {

        // 댓글 등록 api 요청하고 Observable 반환
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .insertComment(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // response 중에 result 만 얻기 위해 map() 함수 사용
                .map(LoadDataResponse::getResult);
    }

    /**
     * 댓글 삭제 조회 api 요청 후 Observable 전달 */
    @Override
    public Observable<String> deleteData(CommentWriteRequest source) {

        // 댓글 삭제 api 요청하고 Observable 반환
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .deleteComment(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // response 중에 result 만 얻기 위해 map() 함수 사용
                .map(LoadDataResponse::getResult);
    }

    /**
     * 댓글 수정 api 요청 후 Observable 전달  */
    @Override
    public Observable<String> updateData(CommentWriteRequest source) {

        // 댓글 수정 api 요청하고 Observable 반환
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .updateComment(source)
                // HTTP 통신을 위한 new Thread 생성
                .subscribeOn(Schedulers.newThread())
                // response 중에 result 만 얻기 위해 map() 함수 사용
                .map(LoadDataResponse::getResult);
    }

}
