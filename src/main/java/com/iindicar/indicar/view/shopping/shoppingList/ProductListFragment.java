package com.iindicar.indicar.view.shopping.shoppingList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.shopping.ShoppingRepository;
import com.iindicar.indicar.databinding.FragmentProductListBinding;
import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;
import com.iindicar.indicar.view.shopping.shoppingDetail.ShoppingDetailActivity;

public class ProductListFragment extends BaseFragment<FragmentProductListBinding> implements ProductListContract.View, OnPageSelectedListener {

    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final int PRODUCT_TYPE_BEST = 1;
    public static final int PRODUCT_TYPE_NEW = 2;
    public static final int PRODUCT_TYPE_KONA = 3;
    public static final int PRODUCT_TYPE_SONATA = 4;
    public static final int PRODUCT_TYPE_AVANTE = 5;
    public static final int PRODUCT_TYPE_QM6 = 6;

    private ProductListPresenter presenter;
    private ShoppingListAdapter adapter;

    private int productType;
    private boolean initialized = false;

    public ProductListFragment(){}

    public static ProductListFragment newInstance(int productType) {
        ProductListFragment fragment = new ProductListFragment();

        Bundle args = new Bundle();
        args.putInt(PRODUCT_TYPE, productType);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        productType = args.getInt(PRODUCT_TYPE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ProductListPresenter(this, ShoppingRepository.getInstance(), productType);
        binding.setPresenter(presenter);

        adapter = new ShoppingListAdapter(context, ShoppingListAdapter.VIEW_TYPE_ALL);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

    }

    private void initView() {

        // 전체 상품 리사이클러 뷰 생성
        binding.recyclerProduct.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerProduct.setAdapter(adapter);
        binding.recyclerProduct.setNestedScrollingEnabled(false);
        binding.recyclerProduct.setItemViewCacheSize(30);

        // 전체 상품 최하단 스크롤 감지
        binding.recyclerProduct.setOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!binding.recyclerProduct.canScrollVertically(1)){
                    presenter.loadItems(false);
                }
            }
        });

        // 최상단 refresh 리스너 등록
        binding.swipeLayout.setOnRefreshListener(() -> presenter.loadItems(true));
    }

    @Override
    public void startShoppingDetailActivity(Product product) {
        Intent intent = new Intent(context, ShoppingDetailActivity.class);
        intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, product.getProductName());
        intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, product.getWebUrl());
        startActivity(intent);
    }

    @Override
    public void onPageSelected() {

        if(!initialized) {
            presenter.onViewCreated();
            initialized = true;
        }
    }
}
