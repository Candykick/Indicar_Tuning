package com.iindicar.indicar.view.community.comment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.databinding.ObservableBoolean;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.comment.CommentRepository;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Comment;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.listener.OnItemClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class CommentPresenter implements CommentContract.Presenter, OnItemClickListener {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    public final ObservableBoolean isPageEnd = new ObservableBoolean(false);

    private CommentContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Comment> adapterModel;
    private CommentRepository repository;

    private static final int PAGE_UNIT = 10;
    private final String boardId;
    private int currentPage = 0;

    private boolean isUpdating = false;
    private boolean isReplying = false;
    private int commentId;
    private int parentId;
    private String commentText;

    public CommentPresenter(CommentContract.View view, CommentRepository repository, String boardId){
        this.view = view;
        this.repository = repository;
        this.boardId = boardId;
    }

    @Override
    public void onViewCreated() {
        loadItems(true);
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        this.adapterView = view;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        this.adapterModel = model;
    }

    /**
     * CommentRepository 에 댓글 정보 요청 후 콜백 받아서 어댑터에게 데이터 넘겨준다. */
    @Override
    public void loadItems(boolean isRefresh) {

        if(isRefresh){
            currentPage = 0;
            isPageEnd.set(false);
            adapterModel.clearItems();
        }

        // 마지막 페이지가 아니고, 데이터 로딩중이 아닌 경우 loadBoards() 호출
        if(!isPageEnd.get() && !isLoading.get()) {
            loadItems(++currentPage, PAGE_UNIT);
        }
    }

    @SuppressLint("CheckResult")
    private void loadItems(int pageIndex, int pageUnit){

        isLoading.set(true);

        // 댓글 리스트 요청 후 UI 변경을 위해 main thread 에서 구독
        repository.loadDataList(RequestMapper.commentListMapping(boardId, pageIndex, pageUnit))
                // UI 변경을 위해 main thread 에서 구독
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commentList -> {
                    isLoading.set(false);
                    adapterModel.addItems(commentList);
                }, error -> {
                    isLoading.set(false);
                    isPageEnd.set(true);
                });
    }

    /**
     * 댓글 등록 버튼 클릭 */
    @SuppressLint("CheckResult")
    @Override
    public void onCommentSubmitButtonClicked(String text) {

        if (!isUpdating) {

            if(!isReplying) {
                // 댓글 등록 요청 후 리스트 갱신
                repository.insertData(RequestMapper.commentWriteMapping(this.boardId, text))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success -> loadItems(true), error -> {
                        });
            } else {
                // 대댓글 등록 요청 후 리스트 갱신
                repository.insertData(RequestMapper.commentReplyMapping(this.boardId, parentId, text))
                        .subscribe(success -> {
                            isReplying = false;
                            loadItems(true);
                        }, error -> isReplying = false);
            }

        } else {

            if(commentText.equals(text)){
                return;
            }

            // 댓글 수정 요청 후 리스트 갱신
            repository.updateData(RequestMapper.commentUpdateMapping(this.boardId, this.commentId, text))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success -> {
                        isUpdating = false;
                        loadItems(true);
                    }, error -> isUpdating = false);
        }
    }

    /**
     * 댓글 메뉴 버튼 리스너 callback */
    @Override
    public void onItemClick(final int position) {

        Comment item = adapterModel.getItem(position);

        String loginUserId = UserRepository.getInstance().getLoginUser().getUserId();

        if(!item.getUserId().equals(loginUserId)){
            // 다른 사람 댓글인 경우 대댓글

            // 클릭된 댓글의 parent id
            this.parentId = item.getParentId();
            this.isReplying = true;

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
                            isUpdating = true;
                            view.onStartCommentUpdate(this.commentText);
                            break;
                        case 1: // 댓글 삭제
                            // 댓글 삭제 요청 후 완료 여부 돌아오면 댓글 조회를 다시 요청
                            repository.deleteData(RequestMapper.commentDeleteMapping(boardId, commentId))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(success -> {
                                        loadItems(true);
                                        view.onCommentDeleted();
                                    }, error -> {});
                            break;
                    }
                }).show();
    }
}
