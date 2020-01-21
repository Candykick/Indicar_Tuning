package com.iindicar.indicar.view.notice.noticeDetail;

import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface NoticeDetailContract {

    interface View extends BaseView<Presenter> {

        void onNoticeLoaded(Notice board);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems();
    }
}
