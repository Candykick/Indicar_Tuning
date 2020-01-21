package com.iindicar.indicar.view.album;

import com.iindicar.indicar.data.local.GalleryVO;
import com.iindicar.indicar.data.source.gallery.GalleryDataSource;
import com.iindicar.indicar.data.source.gallery.GalleryRepository;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AlbumPresenter implements AlbumContract.Presenter, OnItemClickListener {

    private AlbumContract.View view;
    private AdapterContract.View adapterView;
    private AdapterContract.Model<GalleryVO> adapterModel;

    private GalleryRepository repository;

    private List<GalleryVO> selectedItems = new ArrayList<>();

    public AlbumPresenter(AlbumContract.View view, GalleryRepository repository){
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void setAdapterView(AdapterContract.View view) {
        adapterView = view;
        adapterView.setOnItemClickListener(this);
    }

    @Override
    public void setAdapterModel(AdapterContract.Model model) {
        adapterModel = model;
    }

    @Override
    public void loadItems() {

        repository.getImages(new GalleryDataSource.LoadImageListCallback<GalleryVO>() {
            @Override
            public void onImageListLoaded(List<GalleryVO> items) {
                if(items != null){
                    adapterModel.updateItems(items);
                    view.setImagesCount(items.size());
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void onViewDestroyed() {
        this.view = null;
    }

    @Override
    public void onItemClick(int position) {

        GalleryVO item = adapterModel.getItem(position);

        if (item.isSelected() && selectedItems.contains(item)){
            // 선택된 item 이 클릭된 경우
            // 선택 목록에서 지우고 index 숫자 없애기
            selectedItems.remove(item);
            item.setIndex("");
            // 선택 목록 나머지 index 숫자 변경
            for(GalleryVO vo : selectedItems){
                vo.setIndex("" + (selectedItems.indexOf(vo) + 1));
            }
        } else {
            // 선택 목록에 추가하고 index 추가
            selectedItems.add(item);
            item.setIndex("" + (selectedItems.indexOf(item) + 1));
        }
        // 배경 색 binding
        item.setSelected(!item.isSelected());
    }

    @Override
    public void onDoneButtonClicked() {
        String[] pathArr = new String[selectedItems.size()];
        for(int i = 0 ; i < selectedItems.size() ; i++){
            pathArr[i] = selectedItems.get(i).getMediaPath();
        }
        view.finishActivityWithResult(pathArr);
    }

}
