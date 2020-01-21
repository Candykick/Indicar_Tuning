package com.iindicar.indicar.view.shopping;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.FragmentShoppingBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.listener.OnMenuClickListener;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;
import com.iindicar.indicar.view.shopping.shoppingHome.ShoppingHomeFragment;
import com.iindicar.indicar.view.shopping.shoppingList.ProductListFragment;

public class ShoppingFragment extends BaseFragment<FragmentShoppingBinding> implements ShoppingHomeFragment.onMoreClickListener, OnPageSelectedListener{

    private OnMenuClickListener drawerButtonClickListener;

    private final int NUM_OF_TAB = 7;
    private final int tabNames[] = {
            R.string.shopping_tab_home,
            R.string.shopping_tab_best,
            R.string.shopping_tab_new,
            R.string.shopping_tab_kona,
            R.string.shopping_tab_sonata,
            R.string.shopping_tab_avante,
            R.string.shopping_tab_qm6
    };

    private boolean initialized = false;
    private ShoppingPagerAdapter adapter;

    public ShoppingFragment(){}

    public static ShoppingFragment newInstance() {

        return new ShoppingFragment();
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
        return R.layout.fragment_shopping;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_shopping);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_menu);
        binding.toolbar.btnLeft.setOnClickListener(v -> drawerButtonClickListener.onDrawerButtonClicked());
    }

    private void initView() {

        adapter = new ShoppingPagerAdapter(getChildFragmentManager());

        // 뷰페이저 생성
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(NUM_OF_TAB-1);
        binding.viewPager.setCurrentItem(0);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageSelected(int position) {
                // 페이지 로딩 요청
                Fragment fragment = adapter.getItem(position);
                if(fragment instanceof OnPageSelectedListener){
                    ((OnPageSelectedListener) fragment).onPageSelected();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        ViewCompat.setNestedScrollingEnabled(binding.viewPager, false);

        // 탭 버튼 생성
        for(int i = 0 ; i < NUM_OF_TAB ; i++){
            binding.tabLayout.getTabAt(i).setText(tabNames[i]);
        }
    }

    @Override
    public void onMoreButtonClick(int boardType) {
        switch (boardType){
            case ProductListFragment.PRODUCT_TYPE_BEST:
                binding.viewPager.setCurrentItem(1, true);
                break;
            case ProductListFragment.PRODUCT_TYPE_NEW:
                binding.viewPager.setCurrentItem(2, true);
                break;
        }
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

    private class ShoppingPagerAdapter extends FragmentStatePagerAdapter {

        private Fragment[] fragments = new Fragment[NUM_OF_TAB];

        public ShoppingPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments[0] = ShoppingHomeFragment.newInstance();
            fragments[1] = ProductListFragment.newInstance(ProductListFragment.PRODUCT_TYPE_BEST);
            fragments[2] = ProductListFragment.newInstance(ProductListFragment.PRODUCT_TYPE_NEW);
            fragments[3] = ProductListFragment.newInstance(ProductListFragment.PRODUCT_TYPE_KONA);
            fragments[4] = ProductListFragment.newInstance(ProductListFragment.PRODUCT_TYPE_SONATA);
            fragments[5] = ProductListFragment.newInstance(ProductListFragment.PRODUCT_TYPE_AVANTE);
            fragments[6] = ProductListFragment.newInstance(ProductListFragment.PRODUCT_TYPE_QM6);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return NUM_OF_TAB;
        }

    }
}
