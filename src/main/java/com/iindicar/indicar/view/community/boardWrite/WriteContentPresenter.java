package com.iindicar.indicar.view.community.boardWrite;

import android.util.Log;

import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;
import com.iindicar.indicar.view.adapter.AdapterContract;

import java.util.List;

public class WriteContentPresenter implements BoardWriteContract.ContentPresenter {

    private static final String TAG = "WriteContentPresenter";
    private BoardWriteContract.ContentView view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<BoardFile> adapterModel;

    private BoardRepository repository;

    private BoardWrite boardWrite;

    public WriteContentPresenter(BoardWriteContract.ContentView view, BoardRepository repository){
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        adapterView = view;
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        adapterModel = model;
    }

    @Override
    public void loadItems() {

        // 프래그먼트 전환시 저장한 캐시 가져오기
        boardWrite = repository.getCacheWriteBoard();

        if(boardWrite != null) {
            // 어댑터에 데이터 전달
            adapterModel.addItems(boardWrite.getFileList());
        }
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

}
