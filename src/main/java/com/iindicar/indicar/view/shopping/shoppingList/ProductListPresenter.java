package com.iindicar.indicar.view.shopping.shoppingList;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;

import com.iindicar.indicar.data.source.shopping.ShoppingRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.adapter.AdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ProductListPresenter implements ProductListContract.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    private ProductListContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<Product> adapterModel;
    private ShoppingRepository repository;

    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    private static final int PAGE_UNIT = 60;
    //private int currentPage = 0;
    private boolean isPageEnd = true;

    private final int productType;

    public ProductListPresenter(ProductListContract.View view, ShoppingRepository repository, int productType) {
        this.view = view;
        this.repository = repository;
        this.productType = productType;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        this.adapterView = view;
        this.adapterView.setOnItemClickListener(position ->
                this.view.startShoppingDetailActivity(adapterModel.getItem(position)));
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        this.adapterModel = model;
    }

    @Override
    public void loadItems(boolean isRefresh) {

        // 새로고침 한 경우 end 플래그 OFF, 현재페이지 초기화, 어댑터 초기화
        if (isRefresh){
            isPageEnd = false;
            //currentPage = 0;
            adapterModel.clearItems();
        }

        // 마지막 페이지가 아니고, 데이터 로딩중이 아닌 경우 loadBoards() 호출
        if(!isPageEnd && !isLoading.get()) {
            loadProducts();
        }
    }

    @SuppressLint("CheckResult")
    private void loadProducts() {

        isLoading.set(true);

        // 쇼핑 리스트 요청 ++currentPage
        repository.loadDataList(RequestMapper.shoppingListMapping(1, PAGE_UNIT))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productList -> {
                    // 어댑터에 리스트 추가
                    switch (productType){
                        case ProductListFragment.PRODUCT_TYPE_BEST:
                            for(Product product : productList){
                                if("Y".equals(product.getIsBest())){
                                    adapterModel.addItem(product);
                                }
                            }
                            break;
                        case ProductListFragment.PRODUCT_TYPE_NEW:
                            for(Product product : productList){
                                if("Y".equals(product.getIsNew())){
                                    adapterModel.addItem(product);
                                }
                            }
                            break;
                        case ProductListFragment.PRODUCT_TYPE_KONA:
                            for(Product product : productList) {
                                if("kona".equals(product.getCarSpecName())) {
                                    adapterModel.addItem(product);
                                }
                            }
                            break;
                        case ProductListFragment.PRODUCT_TYPE_SONATA:
                            for(Product product : productList) {
                                if("sonata".equals(product.getCarSpecName())) {
                                    adapterModel.addItem(product);
                                }
                            }
                            break;
                        case ProductListFragment.PRODUCT_TYPE_AVANTE:
                            for(Product product : productList) {
                                if("avante".equals(product.getCarSpecName())) {
                                    adapterModel.addItem(product);
                                }
                            }
                            break;
                        case ProductListFragment.PRODUCT_TYPE_QM6:
                            for(Product product : productList) {
                                if("qm6".equals(product.getCarSpecName())) {
                                    adapterModel.addItem(product);
                                }
                            }
                            break;
                    }
                    // 프로그래스바 종료
                    isLoading.set(false);
                    // 요청결과 개수가 page unit 보다 작으면 end flag ON
                    if(productList.size() < PAGE_UNIT) {
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
}
