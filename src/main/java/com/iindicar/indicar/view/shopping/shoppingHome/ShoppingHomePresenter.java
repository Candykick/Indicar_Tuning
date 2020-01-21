package com.iindicar.indicar.view.shopping.shoppingHome;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.iindicar.indicar.data.source.shopping.ShoppingRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.shopping.shoppingList.ProductListFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ShoppingHomePresenter implements ShoppingHomeContract.Presenter {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    public final ObservableInt currentImage = new ObservableInt(0);

    private static final int PAGE_UNIT = 20;

    private ShoppingHomeContract.View view;
    private AdapterContract.View bestView;
    private AdapterContract.Model<Product> bestModel;
    private AdapterContract.View newView;
    private AdapterContract.Model<Product> newModel;
    private ShoppingRepository repository;

    private int currentPage = 0;
    private boolean isPageEnd = true;

    public ShoppingHomePresenter(ShoppingHomeContract.View view, ShoppingRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void loadItems(boolean isRefresh) {

        // 새로고침 한 경우 end 플래그 OFF, 현재페이지 초기화, 어댑터 초기화
        if (isRefresh){
            isPageEnd = false;
            currentPage = 0;
            bestModel.clearItems();
            newModel.clearItems();
        }

        // 마지막 페이지가 아니고, 데이터 로딩중이 아닌 경우 loadBoards() 호출
        if(!isPageEnd && !isLoading.get()) {
            loadProducts();
        }
    }

    @SuppressLint("CheckResult")
    private void loadProducts() {

        isLoading.set(true);

        // 쇼핑 리스트 요청
        repository.loadDataList(RequestMapper.shoppingListMapping(++currentPage, PAGE_UNIT))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productList -> {
                    // 어댑터에 리스트 추가
                    for(Product product : productList){

                        if("Y".equals(product.getIsBest())){  // 베스트
                            bestModel.addItem(product);
                        }

                        if ("Y".equals(product.getIsNew())){  // 신상품
                            newModel.addItem(product);
                        }
                    }
                    // 프로그래스바 종료
                    isLoading.set(false);
                    // 요청결과 개수가 page unit 보다 작으면 end flag ON
                    if(productList.size() < PAGE_UNIT){
                        isPageEnd = true;
                    }
                }, error -> {
                    // 요청 결과 없는 경우 프로그래스바 종료하고 end flag ON
                    isPageEnd = true;
                    isLoading.set(false);
                });
    }

    @Override
    public void onViewCreated() {
        loadItems(true);
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

    @Override
    public void setBestAdapterView(AdapterContract.View view) {
        this.bestView = view;
        this.bestView.setOnItemClickListener(position ->
                this.view.startShoppingDetailActivity(bestModel.getItem(position)));
    }

    @Override
    public void setBestAdapterModel(AdapterContract.Model model) {
        this.bestModel = model;
    }

    @Override
    public void setNewAdapterView(AdapterContract.View view) {
        this.newView = view;
        this.newView.setOnItemClickListener(position ->
                this.view.startShoppingDetailActivity(newModel.getItem(position)));
    }

    @Override
    public void setNewAdapterModel(AdapterContract.Model model) {
        this.newModel = model;
    }
}
