package com.iindicar.indicar.view.community.comment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.comment.CommentRepository;
import com.iindicar.indicar.databinding.ActivityCommentBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.utils.KeyboardUtil;
import com.iindicar.indicar.view.BaseActivity;

public class CommentActivity extends BaseActivity<ActivityCommentBinding> implements CommentContract.View {

    public static final String EXTRA_BOARD_ID = "EXTRA_BOARD_ID";

    private CommentPresenter presenter;
    private CommentAdapter adapter;

    private String boardId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boardId = getBoardId();

        presenter = new CommentPresenter(this, CommentRepository.getInstance(), boardId);
        binding.setPresenter(presenter);

        adapter = new CommentAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated(); // 뷰 생성 후 호출
    }

    private void initView() {

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_community);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_back);
        binding.toolbar.btnLeft.setOnClickListener(v -> onBackPressed());

        // 리사이클러 뷰 생성
        binding.recyclerComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerComment.setAdapter(adapter);

        // 최하단 스크롤 감지
        binding.recyclerComment.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!binding.recyclerComment.canScrollVertically(1)){
                    presenter.loadItems(false);
                }
            }
        });

        // 댓글 등록 버튼 리스너
        binding.commentBox.btnSubmit.setOnClickListener(v -> {
            String text = binding.commentBox.etComment.getText().toString();
            if(text.length() == 0){
                return;
            }
            presenter.onCommentSubmitButtonClicked(text);
            binding.commentBox.etComment.setText("");
            KeyboardUtil.closeKeyboard(this, binding.commentBox.etComment);
        });
    }

    /**
     * BoardDetailActivity 에서 Intent 로 넘겨준 board_id 가져오기 */
    public String getBoardId() {
        return getIntent().getStringExtra(EXTRA_BOARD_ID);
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
    public void onBackPressed() {
        KeyboardUtil.closeKeyboard(this, binding.commentBox.etComment);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onStartCommentUpdate(String commentText) {
        binding.commentBox.etComment.setText(commentText);
        binding.commentBox.etComment.setSelection(commentText.length());
        new Handler().postDelayed(() ->
                KeyboardUtil.openKeyboard(getApplicationContext(), binding.commentBox.etComment), 100);
    }

    @Override
    public void onStartCommentReply(String parentName) {
        StringBuilder sb = new StringBuilder("@");
        sb.append(parentName);
        sb.append(" ");
        binding.commentBox.etComment.setText(sb.toString());
        binding.commentBox.etComment.setSelection(sb.length());
        new Handler().postDelayed(() ->
                KeyboardUtil.openKeyboard(getApplicationContext(), binding.commentBox.etComment), 100);
    }

    /**
     * 댓글 삭제 완료 후 호출되는 callback */
    @Override
    public void onCommentDeleted() {
        makeToast("댓글 1개가 삭제되었습니다.");
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
