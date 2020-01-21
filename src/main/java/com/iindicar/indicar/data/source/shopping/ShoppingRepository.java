package com.iindicar.indicar.data.source.shopping;

import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.ShoppingDetailRequest;
import com.iindicar.indicar.data.remote.ShoppingListRequest;
import com.iindicar.indicar.model.Product;

import java.util.List;

import io.reactivex.Observable;

/**
 * 쇼핑 리스트 조회
 * */
public class ShoppingRepository implements ShoppingDataSource {

    private static ShoppingRepository shoppingRepository;

    private ShoppingRemoteDataSource remoteDataSource;

    private ShoppingRepository() {
        remoteDataSource = ShoppingRemoteDataSource.getInstance();
    }

    public static ShoppingRepository getInstance() {
        if(shoppingRepository == null){
            shoppingRepository = new ShoppingRepository();
        }
        return shoppingRepository;
    }

    @Override
    public Observable<Product> loadData(ShoppingDetailRequest source) {
        return remoteDataSource.loadData(source);
    }

    @Override
    public Observable<List<Product>> loadDataList(ShoppingListRequest source) {
        return remoteDataSource.loadDataList(source);
    }
}
