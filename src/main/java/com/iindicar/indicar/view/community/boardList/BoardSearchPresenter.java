package com.iindicar.indicar.view.community.boardList;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.view.View;

import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.listener.OnItemClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class BoardSearchPresenter implements BoardListContract.SearchPresenter, OnItemClickListener {

    public final ObservableField<String> searchKey = new ObservableField<>("");
    public final ObservableField<String> boardType = new ObservableField<>();

    private BoardListContract.SearchView view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Board> adapterModel;
    private BoardRepository repository;

    private final int PAGE_UNIT = 30;
    private int currentPage = 0;
    private boolean isSearchActive = false;

    public BoardSearchPresenter(BoardListContract.SearchView view, BoardRepository repository){
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onViewCreated() {
        //loadItems(false);
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        this.adapterView = view;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        this.adapterModel = model;
    }

    @Override
    public void loadItems(boolean isRefresh) {

        if(!isSearchActive) return;

        view.setRefreshing(true);

        if (isRefresh){
            adapterModel.clearItems();
            currentPage = 0;
        }

        loadSearchedBoards();
    }

    /**
     * 검색 완료 버튼 클릭 callback */
    @Override
    public void onSubmitButtonClicked() {
        isSearchActive = true;
        currentPage = 0;
        loadItems(true);
    }

    /**
     * 게시판 필터 버튼 클릭 callback */
    @Override
    public void onBoardFilterClicked(View view){
        boardType.set(view.getTag().toString());
    }

    @SuppressLint("CheckResult")
    private void loadSearchedBoards() {

        // 게시물 검색 리스트 요청
        repository.loadDataList(RequestMapper.searchBoardListMapping(boardType.get(), searchKey.get(), ++currentPage, PAGE_UNIT, LocaleHelper.getLanguage(view.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boardList -> {
                    // 어댑터에 리스트 추가
                    adapterModel.addItems(boardList);
                    view.setRefreshing(false);

                }, error -> view.onLoadBoardsFail());
    }

    /**
     * 게시물 클릭시 해당 게시물의 상세 조회 페이지로 이동 */
    @Override
    public void onItemClick(int position) {
        view.startBoardDetailActivity(adapterModel.getItem(position));
    }
}
