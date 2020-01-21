package com.iindicar.indicar.view.community.boardWrite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;
import com.iindicar.indicar.view.adapter.AdapterContract;

import java.util.ArrayList;
import java.util.List;

import static com.iindicar.indicar.view.album.AlbumActivity.RESULT_PATH_ARRAY;
import static com.iindicar.indicar.view.community.boardWrite.WriteFilterFragment.REQUEST_PICK_FROM_ALBUM;

public class WriteFilterPresenter implements BoardWriteContract.FilterPresenter {

    private static final String TAG = "WriteFilterPresenter";

    public final ObservableField<String> boardType = new ObservableField<>();

    private BoardWriteContract.FilterView view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<BoardFile> adapterModel;
    private BoardRepository repository;

    private final boolean isUpdating;

    public WriteFilterPresenter(BoardWriteContract.FilterView view, BoardRepository repository, boolean isUpdating) {
        this.view = view;
        this.repository = repository;
        this.isUpdating = isUpdating;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        adapterView = view;
    }

    @SuppressLint("CheckResult")
    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        adapterModel = model;
    }

    @Override
    public void loadItems() {

        // 캐시 참조 가져옴
        BoardWrite cacheBoard = repository.getCacheWriteBoard();

        // 캐시에 정보가 없으면 리턴
        if(cacheBoard == null){
            boardType.set(view.getContext().getString(R.string.daylife));
            return;
        }

        // 필터 저장
        boardType.set(cacheBoard.getBoardType());

        // 캐시에 저장된 사진이 있으면 어댑터에 add
        List<BoardFile> fileList = cacheBoard.getFileList();
        if(fileList != null && fileList.size() > 0){
            adapterModel.updateItems(fileList);
        }
    }

    @Override
    public void onActivityResult(int requestCode, Intent data) {

        if (requestCode == REQUEST_PICK_FROM_ALBUM){

            // 앨범에서 받아온 사진 path
            String[] pathArr = data.getStringArrayExtra(RESULT_PATH_ARRAY);

            // 선택된 사진 없으면 return
            if(pathArr == null || pathArr.length == 0) {
                return;
            }

            // 사진 저장할 list 생성
            List<BoardFile> items = new ArrayList<>();
            for(String path : pathArr){
                BoardFile boardFile = new BoardFile();
                boardFile.setFilePath(path);
                boardFile.setUploadFlag(BoardFile.FLAG_INSERT);
                items.add(boardFile);
            }

            // 어댑터에 add
            adapterModel.addItems(items);
        }
    }

    @Override
    public void onNextButtonClicked() {

        // 선택된 사진이 없으면 뷰에 알리고 리턴
        if(adapterModel.getItemCount() == 0) {
            view.makeToast(view.getContext().getResources().getString(R.string.picture_list_empty));
            return;
        }

        if(isUpdating){ // 수정하는 경우 레파지토리에 있는 캐시에 fileList 저장
            repository.getCacheWriteBoard().setFileList(adapterModel.getItemList());
        } else { // 새로 작성하는 경우 새 객체 생성 후 필터 저장
            BoardWrite board = new BoardWrite();
            board.setBoardType(boardType.get());
            board.setFileList(adapterModel.getItemList());
            // 레파지토리에 저장
            repository.setCacheWriteBoard(board);
        }

        // 프래그먼트 전환
        view.onStartContentFragment();
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void onViewDestroyed() {
        view = null;
    }

}
