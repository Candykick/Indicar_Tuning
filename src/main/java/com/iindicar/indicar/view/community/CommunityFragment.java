package com.iindicar.indicar.view.community;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.FragmentCommunityBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.community.boardList.BoardListFragment;
import com.iindicar.indicar.view.community.boardList.BoardSearchFragment;
import com.iindicar.indicar.view.community.boardWrite.BoardWriteActivity;
import com.iindicar.indicar.view.listener.OnMenuClickListener;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> implements OnPageSelectedListener {

    private OnMenuClickListener drawerButtonClickListener;

    private final int tabImages[] = {
            R.drawable.tab_board_star,
            R.drawable.tab_board_all,
            R.drawable.tab_board_search
    };

    private boolean initialized = false;

    public CommunityFragment() {}

    public static CommunityFragment newInstance() {

        return new CommunityFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMenuClickListener){
            drawerButtonClickListener = (OnMenuClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFilterFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        drawerButtonClickListener = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_community);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_menu);
        binding.toolbar.btnLeft.setOnClickListener(v -> drawerButtonClickListener.onDrawerButtonClicked());

    }

    private void initView() {

        // 뷰페이저 생성
        binding.viewPagerBoard.setAdapter(new BoardPagerAdapter(getChildFragmentManager()));
        binding.viewPagerBoard.setCurrentItem(0);
        binding.viewPagerBoard.setOffscreenPageLimit(2);

        // 탭 레이아웃 생성
        binding.tabLayoutCommunity.setupWithViewPager(binding.viewPagerBoard);
        // 탭 버튼 등록
        for(int i = 0 ; i < tabImages.length ; i++){
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageView.requestLayout();
            imageView.setImageResource(tabImages[i]);
            binding.tabLayoutCommunity.getTabAt(i).setCustomView(imageView);
        }

        // 글작성 버튼 클릭 리스너 등록
        binding.fabWrite.setOnClickListener(v -> startBoardWriteEditActivity());
    }

    /**
     * 글작성 페이지 열기 */
    private void startBoardWriteEditActivity() {
        Intent intent = new Intent(context, BoardWriteActivity.class);
        startActivity(intent);
    }

    /**
     * 페이지 선택 됐을 때 로딩 */
    @Override
    public void onPageSelected() {

        if(!initialized) {
            initView();
            initialized = true;
        }
    }

    private class BoardPagerAdapter extends FragmentStatePagerAdapter {

        private final int NUM_OF_TAB = 3;

        public BoardPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = BoardListFragment.newInstance(BoardListFragment.BOARD_LIST_STAR);
                    break;
                case 1:
                    fragment = BoardListFragment.newInstance(BoardListFragment.BOARD_LIST_ALL);
                    break;
                case 2:
                    fragment = BoardSearchFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_OF_TAB;
        }
    }
}

