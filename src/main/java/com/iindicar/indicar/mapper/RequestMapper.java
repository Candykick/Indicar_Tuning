package com.iindicar.indicar.mapper;

import com.iindicar.indicar.data.remote.BoardDeleteRequest;
import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.BoardWriteRequest;
import com.iindicar.indicar.data.remote.CommentListRequest;
import com.iindicar.indicar.data.remote.CommentWriteRequest;
import com.iindicar.indicar.data.remote.ShoppingDetailRequest;
import com.iindicar.indicar.data.remote.ShoppingListRequest;
import com.iindicar.indicar.data.remote.UserUpdateRequest;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.LocaleHelper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestMapper {

    private static final String TAG = "RequestMapper";

    public static BoardListRequest noticeListMapping(int pageIndex, int pageUnit, String branchId){

        BoardListRequest request = new BoardListRequest();
        request.setBoardType("notice");
        request.setPageIndex(pageIndex);
        request.setPageUnit(pageUnit);
        request.setBranchId(branchId);
        request.setSearchCnd("");

        return request;
    }

    public static BoardListRequest allBoardListMapping(int pageIndex, int pageUnit, String branchId){

        BoardListRequest request = new BoardListRequest();
        request.setBoardType("all");
        request.setPageIndex(pageIndex);
        request.setPageUnit(pageUnit);
        request.setBranchId(branchId);
        request.setSearchCnd("");

        return request;
    }

    public static BoardListRequest popBoardListMapping(int pageIndex, int pageUnit, String branchId){

        BoardListRequest request = new BoardListRequest();
        request.setBoardType("all");
        request.setPageIndex(pageIndex);
        request.setPageUnit(pageUnit);
        request.setBranchId(branchId);
        request.setSearchCnd("pop");

        return request;
    }

    public static BoardListRequest searchBoardListMapping(String boardType, String searchCnd, int pageIndex, int pageUnit, String branchId){

        BoardListRequest request = new BoardListRequest();
        request.setBoardType(boardType);
        request.setPageIndex(pageIndex);
        request.setPageUnit(pageUnit);
        request.setBranchId(branchId);
        request.setSearchCnd(searchCnd);

        return request;
    }

    public static BoardListRequest likeBoardListMapping(String branchId){

        BoardListRequest request = new BoardListRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setUserId(loginUser.getUserId());
        request.setBranchId(branchId);

        return request;
    }

    public static BoardListRequest myBoardListMapping(String branchId){

        BoardListRequest request = new BoardListRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setUserId2(loginUser.getUserId());
        request.setBranchId(branchId);

        return request;
    }

    public static BoardListRequest myCommentListMapping(int pageIndex, int pageUnit, String branchId){

        BoardListRequest request = new BoardListRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setUserId2(loginUser.getUserId());
        request.setPageIndex(pageIndex);
        request.setPageUnit(pageUnit);
        request.setBranchId(branchId);

        return request;
    }

    public static BoardDetailRequest boardDetailMapping(String boardId, String boardType, String branchId){

        return new BoardDetailRequest(boardId, boardType, branchId);
    }

    public static BoardDetailRequest boardLikeMapping(String boardId){

        BoardDetailRequest request = new BoardDetailRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setUserId(loginUser.getUserId());
        request.setBoardId(boardId);

        return request;
    }

    public static BoardDetailRequest noticeDetailMapping(String noticeId, String branchId) {
        return new BoardDetailRequest(noticeId, "notice", branchId);
    }

    public static BoardDeleteRequest boardDeleteMapping(Board board){

        BoardDeleteRequest request = new BoardDeleteRequest();
        request.setBoardId(board.getBoardId());
        request.setBoardType(board.getBoardType());

        return request;
    }

    private static String getFileIdArray(List<BoardFile> fileList){

        StringBuilder sb = new StringBuilder(fileList.get(0).getFileId());

        int len = fileList.size();
        for(int i = 1 ; i < len ; i++) {
            sb.append(",");
            sb.append(fileList.get(i).getFileId());
        }
        return sb.toString();
    }

    public static BoardWriteRequest boardWriteMapping(BoardWrite source, String branchId){

        BoardWriteRequest request = new BoardWriteRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setBoardTitle("");
        request.setBoardType(source.getBoardType());
        request.setBranchId(branchId);
        request.setCarName("");
        request.setFileIdArr(getFileIdArray(source.getFileList()));
        request.setUserId(loginUser.getUserId());
        request.setUserName(loginUser.getUserName());

        return request;
    }

    public static BoardWriteRequest boardUpdateMapping(BoardWrite source, String branchId){

        BoardWriteRequest request = new BoardWriteRequest();

        request.setBoardId(source.getBoardId());
        request.setBoardTitle("");
        request.setBoardType(source.getBoardType());
        request.setBranchId(branchId);
        request.setFileIdArr(getFileIdArray(source.getFileList()));

        return request;
    }

    public static Map<String, RequestBody> insertFileMapping(BoardFile file) {
        // 멀티파트 요청을 보내기 위한 RequestBody Map 객체 생성
        Map<String, RequestBody> map = new HashMap<>();

        File image = new File(file.getFilePath());
        String text = file.getFileText();
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), image);
        RequestBody requestText = RequestBody.create(MediaType.parse("text/plain"), text);

        //** Retrofit2 부터 멀티파트 요청에 filename="" 필수
        map.put("file\"; filename=\"" + image.getName() + "\"", requestFile);
        map.put("file_cn", requestText);

        return map;
    }

    public static Map<String, RequestBody> updateFileMapping(BoardFile file) {
        // 멀티파트 요청을 보내기 위한 RequestBody Map 객체 생성
        Map<String, RequestBody> map = new HashMap<>();

        String text = file.getFileText();
        String fileId = file.getFileId();
        RequestBody requestFileId = RequestBody.create(MediaType.parse("text/plain"), fileId);
        RequestBody requestText = RequestBody.create(MediaType.parse("text/plain"), text);

        map.put("atch_file_id", requestFileId);
        map.put("file_cn", requestText);

        return map;
    }

    public static CommentListRequest commentListMapping(String boardId, int pageIndex, int pageUnit){

        return new CommentListRequest(boardId, pageIndex, pageUnit);
    }

    public static CommentWriteRequest commentWriteMapping(String boardId, String text){

        CommentWriteRequest request = new CommentWriteRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setBoardId(boardId);
        request.setText(text);
        request.setUserId(loginUser.getUserId());

        return request;
    }

    public static CommentWriteRequest commentReplyMapping(String boardId, int parentId, String text) {

        CommentWriteRequest request = new CommentWriteRequest();

        User loginUser = UserRepository.getInstance().getLoginUser();
        request.setBoardId(boardId);
        request.setText(text);
        request.setUserId(loginUser.getUserId());
        request.setParentId(parentId);

        return request;
    }

    public static CommentWriteRequest commentUpdateMapping(String boardId, int commentId, String text){

        CommentWriteRequest request = new CommentWriteRequest();

        request.setBoardId(boardId);
        request.setText(text);
        request.setCommentId(commentId);

        return request;
    }

    public static CommentWriteRequest commentDeleteMapping(String boardId, int commentId){

        CommentWriteRequest request = new CommentWriteRequest();

        request.setBoardId(boardId);
        request.setCommentId(commentId);

        return request;
    }

    public static ShoppingListRequest shoppingListMapping(int pageIndex, int pageUnit){

        return new ShoppingListRequest(pageIndex, pageUnit, "ko");
    }

    public static ShoppingDetailRequest shoppingDetailMapping(String itemId){

        return new ShoppingDetailRequest(itemId, "ko");
    }

    public static UserUpdateRequest userUpdateMapping(String userName){

        User user = UserRepository.getInstance().getLoginUser();

        return new UserUpdateRequest(user.getUserId(), userName);
    }
}
