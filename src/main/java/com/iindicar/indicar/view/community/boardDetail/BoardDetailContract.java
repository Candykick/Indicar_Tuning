package com.iindicar.indicar.view.community.boardDetail;

import android.annotation.SuppressLint;

import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.view.BasePresenter;
import com.iindicar.indicar.view.BaseView;
import com.iindicar.indicar.view.adapter.AdapterContract;

public interface BoardDetailContract {

    interface View extends BaseView<Presenter> {

        void startCommentDetailActivity();

        void makeToast(String text);

        void startBoardWriteActivity();

        void onBoardDeleted();

        void onCommentDeleted();

        void onLoadBoardFail();

        void onBoardLoaded(Board board);

        void onStartCommentUpdate(String commentText);

        void onStartCommentReply(String parentName);
    }

    interface Presenter extends BasePresenter {

        void setFileView(AdapterContract.View view);

        void setFileModel(AdapterContract.Model model);

        void setCommentView(AdapterContract.View view);

        void setCommentModel(AdapterContract.Model model);

        void loadItems();

        void onCommentSubmitButtonClicked(String text);

        void onMenuButtonClicked();

        void onLikeButtonClicked();
    }
}
