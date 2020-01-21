package com.iindicar.indicar.view.community.boardList;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;

import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.listener.OnItemClickListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class BoardListPresenter implements BoardListContract.Presenter, OnItemClickListener {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    private static final int PAGE_UNIT = 20;
    private final int BOARD_LIST_TYPE;

    private BoardListContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Board> adapterModel;
    private BoardRepository repository;

    private int currentPage = 0;
    private boolean isPageEnd = true;

    public BoardListPresenter(BoardListContract.View view, BoardRepository repository, int boardListType){
        this.view = view;
        this.repository = repository;
        this.BOARD_LIST_TYPE = boardListType;
    }

    @Override
    public void onViewCreated() {
         loadItems(true);
    }

    @Override
    public void loadItems(boolean isRefresh) {

        // 새로고침 한 경우 end 플래그 OFF, 현재페이지 초기화, 어댑터 초기화
        if (isRefresh){
            isPageEnd = false;
            currentPage = 0;
            adapterModel.clearItems();
            // 좋아요 게시물인 경우 dirty flag ON
            if(BOARD_LIST_TYPE == BoardListFragment.BOARD_LIST_LIKE){
                repository.setCacheLikeDirty(true);
            }
        }

        // 마지막 페이지가 아니고, 데이터 로딩중이 아닌 경우 loadBoards() 호출
        if(!isPageEnd && !isLoading.get()) {
            loadBoards(++currentPage, PAGE_UNIT);
        }
    }

    /**
     * 게시물이 수정이나 삭제와 같은 변동사항이 생긴 경우 호출 */
    @Override
    public void loadItemsChanged() {
        isPageEnd = false;
        int itemCount = adapterModel.getItemCount();
        adapterModel.clearItems();
        loadBoards(1, itemCount);
    }

    @SuppressLint("CheckResult")
    private void loadBoards(int pageIndex, int pageUnit){

        isLoading.set(true);

        Observable<List<Board>> result;

        switch (BOARD_LIST_TYPE){
            case BoardListFragment.BOARD_LIST_STAR: default:
                // 추천 게시물 리스트 요청
                result = repository.loadDataList(BoardRepository.DATA_TYPE_POP, RequestMapper.popBoardListMapping(pageIndex, pageUnit, LocaleHelper.getLanguage(view.getContext())));
                break;
            case BoardListFragment.BOARD_LIST_ALL:
                // 전체 게시물 리스트 요청
                result = repository.loadDataList(BoardRepository.DATA_TYPE_ALL, RequestMapper.allBoardListMapping(pageIndex, pageUnit, LocaleHelper.getLanguage(view.getContext())));
                break;
            case BoardListFragment.BOARD_LIST_LIKE:
                // 좋아요 게시물 리스트 요청
                result = repository.loadDataList(BoardRepository.DATA_TYPE_LIKE, RequestMapper.likeBoardListMapping(LocaleHelper.getLanguage(view.getContext())));
                break;
            case BoardListFragment.BOARD_LIST_MY_BOARD:
                // 내가 쓴 게시물 리스트 요청
                result = repository.loadDataList(BoardRepository.DATA_TYPE_MY_BOARD, RequestMapper.myBoardListMapping(LocaleHelper.getLanguage(view.getContext())));
                break;
            case BoardListFragment.BOARD_LIST_MY_COMMENT:
                // 내 댓글 게시물 리스트 요청
                result = repository.loadDataList(BoardRepository.DATA_TYPE_MY_COMMENT, RequestMapper.myBoardListMapping(LocaleHelper.getLanguage(view.getContext())));
                break;
        }

        result.observeOn(AndroidSchedulers.mainThread())
                .subscribe(boardList -> {
                    // 어댑터에 리스트 추가
                    adapterModel.addItems(boardList);
                    // 프로그래스바 종료
                    isLoading.set(false);
                    // 요청결과 개수가 page unit 보다 작으면 end flag ON
                    // 좋아요, 내 글, 내 댓글은 한꺼번에 요청하기 때문에 무조건 end flag ON
                    int count = boardList.size();
                    if(count < PAGE_UNIT
                            || BOARD_LIST_TYPE == BoardListFragment.BOARD_LIST_LIKE
                            || BOARD_LIST_TYPE == BoardListFragment.BOARD_LIST_MY_BOARD
                            || BOARD_LIST_TYPE == BoardListFragment.BOARD_LIST_MY_COMMENT){
                        isPageEnd = true;
                    }
                    view.onBoardListLoaded(count);
                }, error -> {
                    isPageEnd = true;
                    isLoading.set(false);
                    view.onBoardListLoaded(0);
                });
    }

    /**
     * 게시물 클릭시 해당 게시물의 상세 조회 페이지로 이동 */
    @Override
    public void onItemClick(int position) {
        view.startBoardDetailActivity(adapterModel.getItem(position));
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

}
