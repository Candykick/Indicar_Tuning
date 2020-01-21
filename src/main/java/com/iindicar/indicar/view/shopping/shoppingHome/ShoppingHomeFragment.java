package com.iindicar.indicar.view.shopping.shoppingHome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.shopping.ShoppingRepository;
import com.iindicar.indicar.databinding.FragmentShoppingHomeBinding;
import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;
import com.iindicar.indicar.view.shopping.shoppingDetail.ShoppingDetailActivity;
import com.iindicar.indicar.view.shopping.shoppingList.ShoppingListAdapter;
import com.iindicar.indicar.view.shopping.shoppingList.ProductListFragment;

public class ShoppingHomeFragment extends BaseFragment<FragmentShoppingHomeBinding> implements ShoppingHomeContract.View, OnPageSelectedListener {

    private onMoreClickListener moreClickListener;

    private ShoppingHomePresenter presenter;
    private ShoppingListAdapter bestAdapter;
    private ShoppingListAdapter newAdapter;

    private final int MAX_INDEX_OF_IMAGES = 2;
    private LinearLayoutManager layoutManager;
    private Handler handler;
    private Runnable runnable;

    public ShoppingHomeFragment(){}

    public static ShoppingHomeFragment newInstance() {
        return new ShoppingHomeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof onMoreClickListener) {
            moreClickListener = (onMoreClickListener) getParentFragment();
        } else {
            throw new RuntimeException("The parent fragment must implement onMoreClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        moreClickListener = null;
    }

    @Override
    public void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ShoppingHomePresenter(this, ShoppingRepository.getInstance());
        binding.setPresenter(presenter);

        bestAdapter = new ShoppingListAdapter(context, ShoppingListAdapter.VIEW_TYPE_HOME);
        presenter.setBestAdapterModel(bestAdapter);
        presenter.setBestAdapterView(bestAdapter);

        newAdapter  = new ShoppingListAdapter(context, ShoppingListAdapter.VIEW_TYPE_HOME);
        presenter.setNewAdapterModel(newAdapter);
        presenter.setNewAdapterView(newAdapter);

        initView();

        presenter.onViewCreated();


    }

    private void initView() {

        // 이미지 배너 생성
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerImage.setLayoutManager(layoutManager);
        binding.recyclerImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.currentImage.set(layoutManager.findFirstVisibleItemPosition());
            }
        });
        ImageListAdapter adapter = new ImageListAdapter(context);
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(context, ShoppingDetailActivity.class);
            intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, getString(R.string.strShoppingMall));
            intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, "");
            intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
            startActivity(intent);
        });
        adapter.addItem(getString(R.string.bannerShopping1));
        adapter.addItem(getString(R.string.bannerShopping2));
        adapter.addItem(getString(R.string.bannerShopping3));
        binding.recyclerImage.setAdapter(adapter);

        if(handler == null) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (layoutManager.findFirstVisibleItemPosition() != MAX_INDEX_OF_IMAGES) {
                        binding.recyclerImage.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                    } else {
                        binding.recyclerImage.smoothScrollToPosition(0);
                    }
                    handler.postDelayed(this, 4000);
                }
            };
        }
        handler.postDelayed(runnable, 4000);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerImage);

        // 베스트 상품 리사이클러 뷰 생성
        binding.recyclerBest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerBest.setAdapter(bestAdapter);
        binding.recyclerBest.setItemViewCacheSize(30);

        // 신상품 리사이클러 뷰 생성
        binding.recyclerNew.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerNew.setAdapter(newAdapter);
        binding.recyclerNew.setItemViewCacheSize(30);

        // 베스트 상품 더보기
        binding.tvBestMore.setOnClickListener(v ->
                moreClickListener.onMoreButtonClick(ProductListFragment.PRODUCT_TYPE_BEST));

        // 신상품 더보기
        binding.tvNewMore.setOnClickListener(v ->
                moreClickListener.onMoreButtonClick(ProductListFragment.PRODUCT_TYPE_NEW));

        // 최상단 refresh 리스너 등록
        binding.swipeLayoutShopping.setOnRefreshListener(() -> presenter.loadItems(true));
    }

    @Override
    public void startShoppingDetailActivity(Product product) {
        Intent intent = new Intent(context, ShoppingDetailActivity.class);
        intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, product.getItemId());
        intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, product.getProductName());
        intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, product.getWebUrl());
        startActivity(intent);
    }

    @Override
    public void onPageSelected() {

    }

    public interface onMoreClickListener {
        void onMoreButtonClick(int boardType);
    }
}
