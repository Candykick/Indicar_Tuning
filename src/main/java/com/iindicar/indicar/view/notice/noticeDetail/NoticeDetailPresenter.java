package com.iindicar.indicar.view.notice.noticeDetail;

import android.annotation.SuppressLint;

import com.iindicar.indicar.data.source.notice.NoticeRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.adapter.AdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NoticeDetailPresenter implements NoticeDetailContract.Presenter {

    private NoticeDetailContract.View view;
    private String noticeId;
    private NoticeRepository repository;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<BoardFile> adapterModel;

    public NoticeDetailPresenter(NoticeDetailContract.View view, String noticeId, NoticeRepository noticeRepository) {
        this.view = view;
        this.noticeId = noticeId;
        this.repository = noticeRepository;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        this.adapterView = view;
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        this.adapterModel = model;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadItems() {

        repository.loadData(RequestMapper.noticeDetailMapping(noticeId, LocaleHelper.getLanguage(view.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    view.onNoticeLoaded(item);

                    // 게시물 내용 (사진+텍스트) 등록
                     adapterModel.updateItems(item.getFileList());
                }, error -> {});
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }
}
