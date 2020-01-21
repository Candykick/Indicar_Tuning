package com.iindicar.indicar.view.community.boardList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.databinding.FragmentBoardListBinding;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.account.profile.ProfileFragment;
import com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity;
import com.iindicar.indicar.view.listener.OnChangeCountListener;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;

import static android.app.Activity.RESULT_OK;

public class BoardListFragment extends BaseFragment<FragmentBoardListBinding> implements BoardListContract.View {

    private static final String EXTRA_BOARD_LIST_TYPE = "EXTRA_BOARD_LIST_TYPE";

    private static final int REQUEST_BOARD_DETAIL = 111;

    public static final int BOARD_LIST_STAR = 0;
    public static final int BOARD_LIST_ALL = 1;
    public static final int BOARD_LIST_LIKE = 2;
    public static final int BOARD_LIST_MY_BOARD = 3;
    public static final int BOARD_LIST_MY_COMMENT = 4;

    private OnChangeCountListener changeCountListener;

    private int boardListType;

    private BoardListPresenter presenter;
    private BoardListAdapter adapter;

    public BoardListFragment(){}

    public static BoardListFragment newInstance(int boardType){

        BoardListFragment boardFragment = new BoardListFragment();

        Bundle args = new Bundle();
        args.putInt(EXTRA_BOARD_LIST_TYPE, boardType);
        boardFragment.setArguments(args);

        return boardFragment;
    }

    private void getBoardListType() {

        Bundle args = getArguments();

        if(args != null) {
            boardListType = args.getInt(EXTRA_BOARD_LIST_TYPE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_board_list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if(fragment instanceof OnChangeCountListener){
            changeCountListener = (OnChangeCountListener) fragment;
        }
    }

    @Override
    public void onDetach() {
        changeCountListener = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBoardListType();

        presenter = new BoardListPresenter(this, BoardRepository.getInstance(), boardListType);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setPresenter(presenter);

        adapter = new BoardListAdapter(context);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {

        // 게시물 리스트 보여줄 리사이클러 뷰 생성
        binding.recyclerBoardList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerBoardList.setAdapter(adapter);
        binding.recyclerBoardList.setItemViewCacheSize(30);
        binding.recyclerBoardList.setEmptyView(binding.empty);

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

        // 최상단 refresh 리스너 등록
        binding.swipeLayoutBoard.setOnRefreshListener(() -> presenter.loadItems(true));
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
        startActivityForResult(intent, REQUEST_BOARD_DETAIL);
    }

    @Override
    public void onBoardListLoaded(int count) {
        if(changeCountListener != null) {
            changeCountListener.onChangeCount(boardListType, count);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_BOARD_DETAIL) {
            boolean result = data.getBooleanExtra(BoardDetailActivity.RESULT_BOARD_CHANGED, false);
            if(result){
                presenter.loadItemsChanged();
            }
        }
    }

    @Override
    public Context getContext() {
        return context;
    }
}
