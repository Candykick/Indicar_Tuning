package com.iindicar.indicar.view.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.databinding.FragmentAccountBinding;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.account.profile.ProfileFragment;
import com.iindicar.indicar.view.community.boardList.BoardListFragment;
import com.iindicar.indicar.view.listener.OnChangeCountListener;
import com.iindicar.indicar.view.listener.OnMenuClickListener;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AccountFragment extends BaseFragment<FragmentAccountBinding> implements OnPageSelectedListener, OnChangeCountListener {

    private OnMenuClickListener drawerButtonClickListener;

    private boolean initialized = false;

    private final int TAB_LIKE = 0;
    private final int TAB_BOARD = 1;
    private final int TAB_CART = 2;
    private final int TAB_MORE = 3;

    private final int tabNames[] = {
            R.string.account_tab_like,
            R.string.account_tab_my_board,
            R.string.account_tab_cart,
            R.string.account_tab_more
    };

    public AccountFragment(){}

    public static AccountFragment newInstance() {

        return new AccountFragment();
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
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 툴바 생성
        /*GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_account);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_menu);
        binding.toolbar.btnLeft.setOnClickListener(v -> drawerButtonClickListener.onDrawerButtonClicked());*/

    }

    private void initView() {

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_account);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_menu);
        binding.toolbar.btnLeft.setOnClickListener(v -> drawerButtonClickListener.onDrawerButtonClicked());

        // 뷰페이저 생성
        binding.viewPagerAccount.setAdapter(new AccountPagerAdapter(getChildFragmentManager()));
        binding.viewPagerAccount.setOffscreenPageLimit(3);
        binding.viewPagerAccount.setCurrentItem(0);
        binding.tabLayoutAccount.setupWithViewPager(binding.viewPagerAccount);

        // 탭 버튼 생성
        for(int i = 0 ; i < tabNames.length ; i++){
            binding.tabLayoutAccount.getTabAt(i).setCustomView(makeTabButton(i, tabNames[i]));
        }
    }

    private View makeTabButton(int index, int stringResourceId){

        View view = LayoutInflater.from(context).inflate(R.layout.layout_account_tab, null, false);
        ((TextView)view.findViewById(R.id.tv_title)).setText(stringResourceId);
        ImageView ivMore = view.findViewById(R.id.iv_more);
        TextView tvCount = view.findViewById(R.id.tv_count);

        tvCount.setText("0");
        if(index == TAB_MORE){
            tvCount.setVisibility(View.GONE);
            ivMore.setVisibility(View.VISIBLE);
        }

        return view;
    }

    /**
     * 페이지 선택 됐을 때 로딩 */
    @Override
    public void onPageSelected() {
        initView();
    }

    @Override
    public void onChangeCount(int type, int count) {
        switch (type){
            case BoardListFragment.BOARD_LIST_LIKE:
                View likeTab = binding.tabLayoutAccount.getTabAt(0).getCustomView();
                ((TextView)likeTab.findViewById(R.id.tv_count)).setText("" + count);
                break;
            case BoardListFragment.BOARD_LIST_MY_BOARD:
                View myBoardTab = binding.tabLayoutAccount.getTabAt(1).getCustomView();
                ((TextView)myBoardTab.findViewById(R.id.tv_count)).setText("" + count);
                break;
        }
    }

    private class AccountPagerAdapter extends FragmentStatePagerAdapter {

        private final int NUM_OF_TAB = 4;

        public AccountPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = BoardListFragment.newInstance(BoardListFragment.BOARD_LIST_LIKE);
                    break;
                case 1:
                    fragment = BoardListFragment.newInstance(BoardListFragment.BOARD_LIST_MY_BOARD);
                    break;
                case 2:
                    fragment = CartFragment.newInstance();
                    break;
                case 3:
                    fragment = ProfileFragment.newInstance();
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
