package com.iindicar.indicar.view.notice.noticeList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.notice.NoticeRepository;
import com.iindicar.indicar.databinding.ActivityNoticeBinding;
import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.notice.noticeDetail.NoticeDetailActivity;

public class NoticeListActivity extends BaseActivity<ActivityNoticeBinding> implements NoticeListContract.View {

    private NoticeListPresenter presenter;
    private NoticeListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NoticeListPresenter(this, NoticeRepository.getInstance());

        adapter = new NoticeListAdapter(this, NoticeListAdapter.VIEW_TYPE_LIST);
        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());
        binding.toolbar.tvCenter.setText(R.string.notice);

        // 공지사항 리스트
        binding.recyclerNotice.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerNotice.setItemViewCacheSize(35);
        binding.recyclerNotice.setAdapter(adapter);
    }

    @Override
    public void startBoardDetailActivity(Notice notice) {
        Intent intent = new Intent(getApplicationContext(), NoticeDetailActivity.class);
        intent.putExtra(NoticeDetailActivity.EXTRA_NOTICE_ID, notice.getBoardId());
        startActivity(intent);
    }

    @Override
    public void onLoadNoticesFail() {

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
}
