package com.iindicar.indicar.view.notice.noticeList;

import android.annotation.SuppressLint;

import com.iindicar.indicar.data.source.notice.NoticeRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.adapter.AdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NoticeListPresenter implements NoticeListContract.Presenter {

    private NoticeListContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Notice> adapterModel;
    private NoticeRepository repository;

    public NoticeListPresenter(NoticeListContract.View view, NoticeRepository repository){
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onViewCreated() {
        loadItems(false);
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

    @Override
    public void setAdapterView(AdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position ->
                this.view.startBoardDetailActivity(adapterModel.getItem(position)));
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        this.adapterModel = model;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadItems(boolean isRefresh) {

        if(isRefresh){
            adapterModel.clearItems();
        }

        repository.loadDataList(RequestMapper.noticeListMapping(1, 15, LocaleHelper.getLanguage(view.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notices -> adapterModel.addItems(notices),
                        error -> view.onLoadNoticesFail());

    }
}
