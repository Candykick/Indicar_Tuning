package com.iindicar.indicar.view.community.boardDetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.data.source.comment.CommentRepository;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.ActivityBoardDetailBinding;
import com.iindicar.indicar.mapper.BoardMapper;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.utils.KeyboardUtil;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.community.boardWrite.BoardWriteActivity;
import com.iindicar.indicar.view.community.comment.CommentActivity;
import com.iindicar.indicar.view.community.comment.CommentAdapter;

public class BoardDetailActivity extends BaseActivity<ActivityBoardDetailBinding> implements BoardDetailContract.View {

    public static final String EXTRA_BOARD_ID = "EXTRA_BOARD_ID";
    public static final String EXTRA_BOARD_TYPE = "EXTRA_BOARD_TYPE";
    public static final String EXTRA_AUTHOR_ID = "EXTRA_AUTHOR_ID";
    public static final String EXTRA_BOARD_UPDATE = "EXTRA_BOARD_UPDATE";
    public static final String RESULT_BOARD_CHANGED = "RESULT_BOARD_CHANGED";

    private boolean hasChanged = false;

    public static final int REQUEST_COMMENT_LIST = 107;

    public final ObservableBoolean isMyBoard = new ObservableBoolean();

    private BoardDetailPresenter presenter;
    private BoardFileAdapter fileAdapter;
    private CommentAdapter commentAdapter;

    private String boardId;
    private String boardType;
    private String authorId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_board_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBoardExtra();

        // 뷰와 액티비티 바인딩
        binding.content.setActivity(this);

        // 프레젠터 생성
        presenter = new BoardDetailPresenter(this, boardId, boardType, BoardRepository.getInstance(), CommentRepository.getInstance());

        // 글 내용 (이미지+텍스트) 어댑터 생성 후 프레젠터에 등록
        fileAdapter = new BoardFileAdapter(this);
        presenter.setFileModel(fileAdapter);
        presenter.setFileView(fileAdapter);

        // 댓글 어댑터 생성 후 프레젠터에 등록
        commentAdapter = new CommentAdapter(this);
        presenter.setCommentModel(commentAdapter);
        presenter.setCommentView(commentAdapter);

        initView();

        presenter.onViewCreated(); // 뷰 생성 후 호출
    }

    private void initView() {

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> onBackPressed());
        binding.toolbar.tvCenter.setText(getResources().getString(BoardMapper.filterNameToStringResourceId(boardType)));

        // 글 내용 (이미지+텍스트) 리사이클러 뷰 생성
        binding.content.recyclerFile.setNestedScrollingEnabled(false);
        binding.content.recyclerFile.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.content.recyclerFile.setAdapter(fileAdapter);

        // 글 메뉴 버튼 클릭 리스너 등록
        binding.content.btnMenu.setOnClickListener(v -> presenter.onMenuButtonClicked());

        // 댓글 최신순으로 맨 아래서부터 표시
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        // 댓글 리사이클러 뷰 생성
        binding.content.recyclerComment.setNestedScrollingEnabled(false);
        binding.content.recyclerComment.setEmptyView(binding.content.emptyView);
        binding.content.recyclerComment.setLayoutManager(layoutManager);
        binding.content.recyclerComment.setAdapter(commentAdapter);

        // 댓글 더보기 버튼 클릭 리스너 등록
        binding.content.commentMore.setOnClickListener(v -> startCommentDetailActivity());

        // 댓글 등록 버튼 클릭 리스너 등록
        binding.content.commentBox.btnSubmit.setOnClickListener(v -> {
            String text = binding.content.commentBox.etComment.getText().toString();
            if(TextUtils.isEmpty(text) || text.length() == 0){
                return;
            }
            presenter.onCommentSubmitButtonClicked(text);
            binding.content.commentBox.etComment.setText("");
            KeyboardUtil.closeKeyboard(this, binding.content.commentBox.etComment);
            hasChanged = true;
        });

        // 좋아요 버튼 클릭 리스너 등록
        binding.boardHeader.btnLike.setOnClickListener(v -> {
            presenter.onLikeButtonClicked();
            hasChanged = true;
        });

        // 댓글 버튼 클릭 리스너 등록
        binding.boardHeader.btnComment.setOnClickListener(v ->
                KeyboardUtil.openKeyboard(this, binding.content.commentBox.etComment));
    }

    /**
     * BoardListFragment 에서 Intent 로 넘겨준 board_id, board_type, author_id 가져오기
     * 해당 게시물이 로그인 사용자가 작성한 것인지 확인 */
    public void getBoardExtra() {
        boardId = getIntent().getStringExtra(EXTRA_BOARD_ID);
        boardType = getIntent().getStringExtra(EXTRA_BOARD_TYPE);
        authorId = getIntent().getStringExtra(EXTRA_AUTHOR_ID);

        String loginUserId = UserRepository.getInstance().getLoginUser().getUserId();
        isMyBoard.set(authorId.equals(loginUserId));
    }

    /**
     * 프래젠터에서 넘긴 boardDetail 정보 뷰에 셋팅 */
    @Override
    public void onBoardLoaded(Board board) {
        binding.setItem(board);
    }

    @Override
    public void onStartCommentUpdate(String commentText) {
        binding.content.commentBox.etComment.setText(commentText);
        binding.content.commentBox.etComment.setSelection(commentText.length());
        new Handler().postDelayed(() ->
                KeyboardUtil.openKeyboard(getApplicationContext(), binding.content.commentBox.etComment), 100);
    }

    /**
     * 대댓글 작성 */
    @Override
    public void onStartCommentReply(String parentName) {
        StringBuilder sb = new StringBuilder("@");
        sb.append(parentName);
        sb.append(" ");
        binding.content.commentBox.etComment.setText(sb.toString());
        binding.content.commentBox.etComment.setSelection(sb.length());
        new Handler().postDelayed(() ->
            KeyboardUtil.openKeyboard(getApplicationContext(), binding.content.commentBox.etComment), 100);
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getBooleanExtra(EXTRA_BOARD_UPDATE, false)) {
            presenter.loadItems();
            hasChanged = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_COMMENT_LIST) {
            presenter.getComments();
            hasChanged = true;
        }
    }

    /**
     * 게시물 상세 요청 실패시 호출되는 callback */
    @Override
    public void onLoadBoardFail() {
        makeToast("삭제된 게시물입니다.");
        hasChanged = true;
        onBackPressed();
    }

    /**
     * 게시글 수정 버튼 누른 경우 : BoardWriteActivity 실행 */
    @Override
    public void startBoardWriteActivity() {
        Intent intent = new Intent(getApplicationContext(), BoardWriteActivity.class);
        intent.putExtra(BoardWriteActivity.EXTRA_UPDATE_BOARD, true);
        startActivity(intent);
    }

    /**
     * 게시글 삭제 완료 후 호출되는 callback */
    @Override
    public void onBoardDeleted() {

        Toast.makeText(this, getString(R.string.board_delete_done), Toast.LENGTH_SHORT).show();
        hasChanged = true;

        onBackPressed();
    }

    /**
     * 댓글 더보기 버튼 누른 경우 : CommentActivity 실행 */
    @Override
    public void startCommentDetailActivity() {
        Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
        intent.putExtra(CommentActivity.EXTRA_BOARD_ID, boardId);
        startActivityForResult(intent, REQUEST_COMMENT_LIST);
    }

    /**
     * 댓글 삭제 완료 후 호출되는 callback */
    @Override
    public void onCommentDeleted() {
        makeToast(getString(R.string.comment_deleted));
        hasChanged = true;
    }

    @Override
    public void onBackPressed() {
        KeyboardUtil.closeKeyboard(this, binding.content.commentBox.etComment);

        Intent result = new Intent();
        result.putExtra(RESULT_BOARD_CHANGED, hasChanged);
        setResult(RESULT_OK, result);
        finish();
    }
}
