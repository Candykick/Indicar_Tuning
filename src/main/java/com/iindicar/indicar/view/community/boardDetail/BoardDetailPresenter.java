package com.iindicar.indicar.view.community.boardDetail;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.util.Log;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.data.source.comment.CommentRepository;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.mapper.BoardMapper;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;
import com.iindicar.indicar.model.Comment;
import com.iindicar.indicar.utils.CustomDialog;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.listener.OnItemClickListener;

import java.util.Collection;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class BoardDetailPresenter implements BoardDetailContract.Presenter, OnItemClickListener {

    private final String TAG = "BoardDetailPresenter";

    public static final int COMMENT_UNIT = 5;

    private BoardDetailContract.View view;
    private AdapterContract.View fileView;
    private AdapterContract.Model<BoardFile> fileModel;
    private AdapterContract.View commentView;
    private AdapterContract.Model<Comment> commentModel;

    private BoardRepository boardRepository;
    private CommentRepository commentRepository;

    private Board board;
    private final String boardId;
    private final String boardType;

    private boolean isCommentUpdating = false;
    private boolean isCommentReplying = false;
    private int commentId;
    private int parentId;
    private String commentText;

    public BoardDetailPresenter(BoardDetailContract.View view, String boardId, String boardType, BoardRepository boardRepository, CommentRepository commentRepository){
        this.view = view;
        this.boardId = boardId;
        this.boardType = boardType;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void setFileView(AdapterContract.View view) {
        fileView = view;
    }

    @Override
    public void setFileModel(AdapterContract.Model model) {
        fileModel = model;
    }

    @Override
    public void setCommentView(AdapterContract.View commentView) {
        this.commentView = commentView;
        this.commentView.setOnItemClickListener(this);
    }

    @Override
    public void setCommentModel(AdapterContract.Model model) {
        commentModel = model;
    }

    /**
     * 게시물 상세 정보를 요청 후 콜백 받아서 view 와 adapter 에 데이터 넘김 */
    @SuppressLint("CheckResult")
    @Override
    public void loadItems() {

        boardRepository.loadData(RequestMapper.boardDetailMapping(boardId, boardType, LocaleHelper.getLanguage(view.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    // 게시물 정보 저장
                    this.board = item;
                    view.onBoardLoaded(this.board);
                    // 게시물 내용 (사진+텍스트) 등록
                    fileModel.updateItems(this.board.getFileList());

                    getIsLikeBoard();
                    getComments();

                }, error -> view.onLoadBoardFail());
    }

    @SuppressLint("CheckResult")
    private void getIsLikeBoard(){

        // 좋아요 확인하기
        boardRepository.loadDataList(BoardRepository.DATA_TYPE_LIKE, RequestMapper.likeBoardListMapping(LocaleHelper.getLanguage(view.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likeList -> {
                    if(likeList == null || likeList.size() == 0) return;
                    for(Board like : likeList){
                        if(boardId.equals(like.getBoardId())){
                            board.setLikeBoard(true);
                            return;
                        }
                    }
                    board.setLikeBoard(false);
                }, error -> board.setLikeBoard(false));
    }

    /**
     * 댓글 리스트 요청 */
    @SuppressLint("CheckResult")
    public void getComments(){

        // 댓글 리스트 요청 후 UI 변경을 위해 main thread 에서 구독
        // 최신순으로 뒤집어서 댓글 표시
        commentRepository.loadDataList(RequestMapper.commentListMapping(boardId, 1, COMMENT_UNIT))
                // UI 변경을 위해 main thread 에서 구독
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentList -> {

                    for(Comment item : commentList){
                        if(item.getId() == item.getParentId()){ // 댓글인 경우

                            for(int j = commentList.indexOf(item)+1 ; j < commentList.size() ; j++){
                                Comment sub = commentList.get(j);
                                if(sub.getParentId() == item.getId()){ // 내가 부모인 경우 스왑
                                    Collections.swap(commentList, commentList.indexOf(item), j);
                                } else {
                                    break;
                                }

                            }
                        }
                    }
                    // 최신 댓글 3개만 보이고 더보기 +(나머지 댓글 수)
                    commentModel.updateItems(commentList);
                }, error -> {
                    // 댓글 없을 때
                    commentModel.clearItems();
                });
    }

    /**
     * 댓글 등록 버튼 클릭 */
    @SuppressLint("CheckResult")
    @Override
    public void onCommentSubmitButtonClicked(String text) {

        if (!isCommentUpdating) {

            if(!isCommentReplying) {
                // 댓글 등록 요청 후 리스트 갱신
                commentRepository.insertData(RequestMapper.commentWriteMapping(this.boardId, text))
                        .subscribe(success -> {
                            board.setCommentCount(board.getCommentCount() + 1);
                            getComments();
                        }, error -> {});
            } else {
                // 대댓글 등록 요청 후 리스트 갱신
                commentRepository.insertData(RequestMapper.commentReplyMapping(this.boardId, parentId, text))
                        .subscribe(success -> {
                            board.setCommentCount(board.getCommentCount() + 1);
                            isCommentReplying = false;
                            getComments();
                        }, error -> isCommentReplying = false);
            }

        } else {

            if(commentText.equals(text)){
                return;
            }

            // 댓글 수정 요청 후 리스트 갱신
            commentRepository.updateData(RequestMapper.commentUpdateMapping(this.boardId, this.commentId, text))
                    .subscribe(success -> {
                        isCommentUpdating = false;
                        getComments();
                    }, error -> isCommentUpdating = false);
        }
    }

    /**
     * 게시글 메뉴 버튼 클릭 callback */
    @Override
    public void onMenuButtonClicked() {

        // 보여줄 메뉴 목록
        String[] menuItems = view.getContext().getResources().getStringArray(R.array.board_detail_menu);

        new AlertDialog.Builder(view.getContext())
                .setItems(menuItems, ((dialog, menuId) -> {
                    switch (menuId){
                        case 0: // 게시글 수정
                            // 수정할 게시물 캐싱
                            boardRepository.setCacheWriteBoard(BoardMapper.boardToBoardWrite(board));
                            view.startBoardWriteActivity();
                            break;
                        case 1: // 게시글 삭제
                            CustomDialog alert = new CustomDialog(view.getContext(), R.layout.dialog_delete_board,
                                    () -> boardRepository.deleteData(RequestMapper.boardDeleteMapping(board))
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(success -> view.onBoardDeleted(), error -> {}));
                            alert.setCancelable(true);
                            alert.show();
                            break;
                    }
                })).show();
    }

    /**
     * 게시물 좋아요 기능 */
    @SuppressLint("CheckResult")
    @Override
    public void onLikeButtonClicked() {

        boardRepository.setBoardLike(RequestMapper.boardLikeMapping(boardId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // 좋아요 목록 변동사항 알리기위해 dirty flag ON
                    boardRepository.setCacheLikeDirty(true);
                    // 좋아요 버튼 토글
                    board.setLikeBoard(!board.isLikeBoard());
                    // 토글 후 좋아요 개수 바꾸기
                    int count = board.getLikeCount();
                    if(board.isLikeBoard()){ // 방금 좋아요 등록한 경우
                        board.setLikeCount(count + 1);
                    } else { // 방금 좋아요 취소한 경우
                        board.setLikeCount(count - 1);
                    }
                }, error -> {});
    }

    /**
     * 댓글 메뉴 버튼 리스너 callback */
    @Override
    public void onItemClick(final int position) {

        Comment item = commentModel.getItem(position);

        String loginUserId = UserRepository.getInstance().getLoginUser().getUserId();

        // 다른 사람 댓글인 경우 대댓글
        if(!item.getUserId().equals(loginUserId)){

            // 클릭된 댓글의 parent id
            this.parentId = item.getParentId();
            this.isCommentReplying = true;

            view.onStartCommentReply(item.getUserName());
            return;
        }

        // 클릭된 댓글의 id, text
        this.commentId = item.getId();
        this.commentText = item.getText();

        // 보여줄 메뉴 목록
        String[] menuItems = view.getContext().getResources().getStringArray(R.array.comment_item_menu);

        // 다이얼로그 띄우기
        new AlertDialog.Builder(view.getContext())
                // 메뉴 목록, 메뉴 클릭 callback
                .setItems(menuItems, (dialog, menuId) -> {
                    switch (menuId){
                        case 0: // 댓글 수정
                            isCommentUpdating = true;
                            view.onStartCommentUpdate(commentText);
                            break;
                        case 1: // 댓글 삭제
                            // 댓글 삭제 요청 후 완료 여부 돌아오면 댓글 조회를 다시 요청
                            commentRepository.deleteData(RequestMapper.commentDeleteMapping(boardId, commentId))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(success -> {
                                        getComments();
                                        board.setCommentCount(board.getCommentCount() - 1);
                                        view.onCommentDeleted();
                                    }, error -> {});
                            break;
                    }
                }).show();
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void onViewDestroyed() {
        view = null;
    }

}
