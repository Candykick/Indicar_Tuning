package com.iindicar.indicar.data.source.shopping;

import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.ShoppingDetailRequest;
import com.iindicar.indicar.data.remote.ShoppingListRequest;
import com.iindicar.indicar.data.source.LoadData;
import com.iindicar.indicar.data.source.LoadDataList;
import com.iindicar.indicar.model.Product;

public interface ShoppingDataSource extends LoadDataList<ShoppingListRequest, Product>, LoadData<ShoppingDetailRequest, Product> {

}