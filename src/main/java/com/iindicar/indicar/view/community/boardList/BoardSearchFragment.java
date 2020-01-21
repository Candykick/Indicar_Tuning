package com.iindicar.indicar.view.community.boardList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.databinding.FragmentBoardSearchBinding;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.utils.KeyboardUtil;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity;

public class BoardSearchFragment extends BaseFragment<FragmentBoardSearchBinding> implements BoardListContract.SearchView {

    private BoardSearchPresenter presenter;
    private BoardListAdapter adapter;

    private boolean isFilterOpen = true;

    public BoardSearchFragment(){}

    public static BoardSearchFragment newInstance(){

        return new BoardSearchFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_board_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new BoardSearchPresenter(this, BoardRepository.getInstance());

        adapter = new BoardListAdapter(context);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
        presenter.onBoardFilterClicked(binding.btnFilterAll);
    }

    private void initView() {

        binding.setPresenter(presenter);

        // 게시물 리스트 보여줄 리사이클러 뷰 생성
        binding.recyclerBoardList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerBoardList.setAdapter(adapter);
        binding.recyclerBoardList.setItemViewCacheSize(30);

        // 최하단 스크롤 감지
        binding.recyclerBoardList.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!binding.recyclerBoardList.canScrollVertically(1)){
                    presenter.loadItems(false);
                }
            }
        });

        // 필터 버튼 리스너 등록
        binding.btnFilter.setOnClickListener(v -> {
            if(isFilterOpen)
                toggleFilter(false);
            else
                toggleFilter(true);
        });

        // 검색 완료 버튼 리스너 등록
        binding.btnSubmit.setOnClickListener(v -> onSubmitButtonClicked());

        // 검색어 입력창 설정
        binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                onSubmitButtonClicked();
            }
            return false;
        });
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchKey.set(binding.etSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * 검색 버튼 눌렀을 때 */
    private void onSubmitButtonClicked(){
        // 필터창 닫기
        toggleFilter(false);
        // 프레젠터에 전달
        presenter.onSubmitButtonClicked();
        // 키보드 닫기
        KeyboardUtil.closeKeyboard(context, binding.etSearch);
    }

    /**
     * 필터 창 레이아웃 토글 */
    @SuppressLint("ClickableViewAccessibility")
    private void toggleFilter(final boolean isOpen){

        int offset = 0; // 닫기

        if(!isOpen){ // 열기
            offset = binding.filterPanel.getHeight();
        }

        // 애니메이션 시작
        binding.filterPanel.animate().translationY(-offset)
                .withEndAction(() -> isFilterOpen = isOpen)
                .withLayer();
        // 아래 뷰에 터치 전달 설정
        binding.filterPanel.setOnTouchListener((v, event) -> isOpen);
    }

    @Override
    public void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public void startBoardDetailActivity(Board board) {
        Intent intent = new Intent(getActivity(), BoardDetailActivity.class);
        intent.putExtra(BoardDetailActivity.EXTRA_BOARD_ID, board.getBoardId());
        intent.putExtra(BoardDetailActivity.EXTRA_BOARD_TYPE, board.getBoardType());
        intent.putExtra(BoardDetailActivity.EXTRA_AUTHOR_ID, board.getAuthorId());
        startActivity(intent);
    }

    @Override
    public void setRefreshing(boolean refreshing) {

    }

    /**
     * 게시물 리스트 요청 실패시 호출되는 callback */
    @Override
    public void onLoadBoardsFail() {
        setRefreshing(false);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
