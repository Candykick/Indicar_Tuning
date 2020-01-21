package com.iindicar.indicar.view.notice.noticeDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.notice.NoticeRepository;
import com.iindicar.indicar.databinding.ActivityNoticeDetailBinding;
import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.community.boardDetail.BoardFileAdapter;

public class NoticeDetailActivity extends BaseActivity<ActivityNoticeDetailBinding> implements NoticeDetailContract.View {

    public static final String EXTRA_NOTICE_ID = "EXTRA_NOTICE_ID";

    private NoticeDetailPresenter presenter;
    private BoardFileAdapter adapter;

    private String noticeId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getNoticeExtra();

        // 프레젠터 생성
        presenter = new NoticeDetailPresenter(this, noticeId, NoticeRepository.getInstance());

        // 글 내용 (이미지+텍스트) 어댑터 생성 후 프레젠터에 등록
        adapter = new BoardFileAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());
        binding.toolbar.tvCenter.setText(R.string.notice);

        // 글 내용 (이미지+텍스트) 리사이클러 뷰 생성
        binding.recyclerFile.setNestedScrollingEnabled(false);
        binding.recyclerFile.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerFile.setAdapter(adapter);

    }

    /**
     * NOTICEListFragment 에서 Intent 로 넘겨준 board_id, board_type, author_id 가져오기
     * 해당 게시물이 로그인 사용자가 작성한 것인지 확인 */
    public void getNoticeExtra() {
        noticeId = getIntent().getStringExtra(EXTRA_NOTICE_ID);
    }

    /**
     * 프래젠터에서 넘긴 noticeDetail 정보 뷰에 셋팅 */
    @Override
    public void onNoticeLoaded(Notice notice) {
        binding.setItem(notice);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }
}
