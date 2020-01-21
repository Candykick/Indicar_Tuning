package com.iindicar.indicar.view.notice.noticeList;

import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface NoticeListContract {

    interface View extends BaseView<Presenter> {

        void startBoardDetailActivity(Notice notice);

        void onLoadNoticesFail();
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems(boolean isRefresh);
    }
}
