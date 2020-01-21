package com.iindicar.indicar.view.shopping.shoppingDetail;

import com.iindicar.indicar.data.source.shopping.ShoppingRepository;

public class ShoppingDetailPresenter implements ShoppingDetailContract.Presenter{

    private ShoppingDetailContract.View view;
    private ShoppingRepository repository;

    private final String boardId;

    public ShoppingDetailPresenter(ShoppingDetailContract.View view, ShoppingRepository repository, String boardId) {
        this.view = view;
        this.repository = repository;
        this.boardId = boardId;
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void loadItems() {
        // do nothing
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

    @Override
    public void onShareButtonClicked() {

    }

    @Override
    public void onLikeButtonClicked() {

    }

}
