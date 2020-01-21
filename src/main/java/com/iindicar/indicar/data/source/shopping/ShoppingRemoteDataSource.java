package com.iindicar.indicar.data.source.shopping;

import android.util.Log;

import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.LoadDataListResponse;
import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.data.remote.ShoppingDetailRequest;
import com.iindicar.indicar.data.remote.ShoppingListRequest;
import com.iindicar.indicar.mapper.BoardMapper;
import com.iindicar.indicar.model.Product;

import java.util.List;
import java.util.StringTokenizer;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingRemoteDataSource implements ShoppingDataSource {

    private static ShoppingRemoteDataSource shoppingRemoteRepository;

    public static ShoppingRemoteDataSource getInstance() {

        if (shoppingRemoteRepository == null) {
            shoppingRemoteRepository = new ShoppingRemoteDataSource();
        }
        return shoppingRemoteRepository;
    }

    private ShoppingRemoteDataSource() { }

    /**
     * 이거 아직 안씀
     */
    @Override
    public Observable<Product> loadData(ShoppingDetailRequest source) {

        return null;
    }

    /**
     * 쇼핑 리스트 조회 api 요청 후 Observable 반환
     * 1) File Url 앞에 "http://" 붙어있는지 체크해야됨
     * 2) 임시로 품목이름, 가격, url 순으로 넣어놓은 string 파싱 */
    @Override
    public Observable<List<Product>> loadDataList(ShoppingListRequest source) {

        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getProducts(source)
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                            List<Product> shoppingList = response.getContent();
                            Log.d("Indicar Tuning",shoppingList.get(0).getProductName());
                            for(Product shop : shoppingList){
                                // Url 앞에 "http://" 붙어있는지 체크
                                String url = BoardMapper.checkImageUrl(shop.getMainFileUrl());
                                shop.setMainFileUrl(url);
                            }
                            return shoppingList;
                        }
                );
    }
}