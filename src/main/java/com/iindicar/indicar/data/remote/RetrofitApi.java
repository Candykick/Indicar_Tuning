package com.iindicar.indicar.data.remote;

import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.Car;
import com.iindicar.indicar.model.Comment;
import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.model.Version;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface RetrofitApi {

    // 차량 리스트 조회
    @GET("/car/get_car")
    Observable<List<Car>> getCars();

    // 공지사항 리스트 조회
    @POST("community/selectBoardArticles")
    Observable<LoadDataListResponse<Notice>> getNotices(@Body BoardListRequest request);

    // 공지사항 상세 조회
    @POST("community/selectBoardArticle")
    Observable<LoadDataListResponse<Notice>> getNotice(@Body BoardDetailRequest request);

    // 쇼핑 리스트 조회
    @POST("community/shop/selectShopItems")
    Observable<LoadDataListResponse<Product>> getProducts(@Body ShoppingListRequest request);

    // 쇼핑 상세 조회
    @POST("community/shop/selectShopItems")
    Observable<LoadDataResponse<Product>> getProduct(@Body ShoppingDetailRequest request);

    // 게시물 리스트 조회
    @POST("community/selectBoardArticles")
    Observable<LoadDataListResponse<Board>> getBoards(@Body BoardListRequest request);

    // 좋아요 한 게시글 리스트 조회
    @POST("community/selectUserLikeBoardArticle")
    Observable<LoadDataListResponse<Board>> getLikeBoards(@Body BoardListRequest request);

    // 내가 쓴 게시글 리스트 조회
    @POST("community/selectUserMyBoardArticle")
    Observable<LoadDataListResponse<Board>> getMyBoards(@Body BoardListRequest request);

    // 댓글 남긴 게시글 리스트 조회
    @POST("community/selectUserMyCommentList")
    Observable<LoadDataListResponse<Board>> getMyCommentBoards(@Body BoardListRequest request);

    // 게시물 리스트 검색
    @POST("community/selectBoardArticles")
    Observable<LoadDataListResponse<Board>> searchBoards(@Body BoardListRequest request);

    // 게시글 상세 조회
    @POST("community/selectBoardArticle")
    Observable<LoadDataListResponse<Board>> getBoard(@Body BoardDetailRequest request);

    // 파일 상세 조회
    @POST("community/selectFileInfs")
    Observable<LoadDataListResponse<BoardFile>> getFiles(@Body String atch_file_id);

    //dfsdfsd
    // 좋아요 등록/취소
    @POST("community/likeBoardArticle")
    Observable<LoadDataResponse<String>> setBoardLike(@Body BoardDetailRequest request);

    // 댓글 리스트 조회
    @POST("community/selectCommentList")
    Observable<LoadDataListResponse<Comment>> getComments(@Body CommentListRequest request);

    // 이미지 업로드 - Multipart 요청
    @Multipart
    @POST("community/insertFiles")
    Observable<LoadDataResponse<String>> insertFiles(@PartMap Map<String, RequestBody> params);

    // 이미지 수정 - Multipart 요청
    @Multipart
    @POST("community/updateFile")
    Observable<LoadDataResponse<String>> updateFile(@PartMap Map<String, RequestBody> params);

    // 이미지 삭제
//    @POST("community/deleteFile")
//    Observable<LoadDataResponse<String>> deleteFile(@Body Request request);

    // 게시글 등록
    @POST("community/insertBoardArticle")
    Observable<LoadDataResponse<String>> insertBoard(@Body BoardWriteRequest request);

    // 게시글 삭제
    @POST("community/deleteBoardArticle")
    Observable<LoadDataResponse<String>> deleteBoard(@Body BoardDeleteRequest request);

    // 게시글 수정
    @POST("community/updateBoardArticle")
    Observable<LoadDataResponse<String>> updateBoard(@Body BoardWriteRequest request);

    // 댓글 등록
    @POST("community/insertComment")
    Observable<LoadDataResponse<String>> insertComment(@Body CommentWriteRequest request);

    // 댓글 삭제
    @POST("community/deleteComment")
    Observable<LoadDataResponse<String>> deleteComment(@Body CommentWriteRequest request);

    // 댓글 수정
    @POST("community/updateComment")
    Observable<LoadDataResponse<String>> updateComment(@Body CommentWriteRequest request);

    // 버전 가져오기
    @GET("version/version")
    Call<Version> getCurrentVersion();

    // 유저 이름 변경
    @POST("user/updt_user")
    Observable<LoadDataResponse<String>> updateUserName(@Body UserUpdateRequest request);

    // 유저 검색 (by email)
    @FormUrlEncoded
    @POST("user/search_user")
    Call<LoadDataResponse<User>> searchUserByEmail(@Field("email") String email);

    // 유저 검색 (by id)
    @FormUrlEncoded
    @POST("user/search_user2")
    Call<LoadDataResponse<User>> searchUserById(@Field("id") String id);

    // 유저 등록 (String login_method, String name, String email, String profile_img_url)
    @FormUrlEncoded
    @POST("user/add_user")
    Call<LoadDataResponse<User>> addUser(@Field("login_method") String login_method,
                                         @Field("name") String name,
                                         @Field("email") String email,
                                         @Field("user_se") String USR,
                                         @Field("profile_img_url") String profile_img_url);

    // 이메일 인증 요청 (String email, String key)
    @FormUrlEncoded
    @POST("auth/request_auth")
    Call<LoadDataResponse> EmailAuthRequest(@Field("key") String userid, @Field("email") String email);

    //이메일 인증했는지 확인 (String key)
    @FormUrlEncoded
    @POST("auth/search_auth")
    Call<LoadDataResponse> EmailAuthSearch(@Field("key") String key);

    // 서버에서 이메일 인증 삭제(String apiid)
    @FormUrlEncoded
    @POST("auth/remove_auth")
    Call<LoadDataResponse> removeAuth(@Field("key") String apiid);

    // 서버에서 계정 삭제(String id)
    @FormUrlEncoded
    @POST("user/remove_user")
    Call<LoadDataResponse> removeUser(@Field("id") String id);

    //FCM 토큰 업데이트
    @FormUrlEncoded
    @POST("user/checkFcmToken")
    Call<LoadDataResponse> updateFCMToken(@Field("id") String id, @Field("fcm_token") String fcm_token);

}
