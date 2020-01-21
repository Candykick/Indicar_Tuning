package com.iindicar.indicar.view.community.comment;

import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface CommentContract {

    interface View extends BaseView<Presenter> {

        void makeToast(String text);

        void onCommentDeleted();

        void onStartCommentUpdate(String commentText);

        void onStartCommentReply(String parentName);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(AdapterContract.View view);

        void setAdapterModel(AdapterContract.Model model);

        void loadItems(boolean isRefresh);

        void onCommentSubmitButtonClicked(String text);

    }
}
