package com.iindicar.indicar.view.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.gallery.GalleryRepository;
import com.iindicar.indicar.databinding.ActivityAlbumBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseActivity;

public class AlbumActivity extends BaseActivity<ActivityAlbumBinding> implements AlbumContract.View {

    public static final String RESULT_PATH_ARRAY = "RESULT_PATH_ARRAY";

    private AlbumPresenter presenter;
    private AlbumAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new AlbumPresenter(this, GalleryRepository.getInstance(this));

        adapter = new AlbumAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_write);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_back);
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());

        // 사진 리스트 리사이클러 뷰 생성
        binding.recyclerAlbum.setLayoutManager(new GridLayoutManager(this, 4));
        binding.recyclerAlbum.setAdapter(adapter);

        // 완료 버튼 클릭 리스너 등록
        binding.btnDone.setOnClickListener(v -> presenter.onDoneButtonClicked());
    }

    @Override
    public void setImagesCount(int size) {
        binding.tvTotalCount.setText(" (" + size + ")");
    }

    @Override
    public void finishActivityWithResult(String[] pathArr) {
        Intent resultData = new Intent();
        resultData.putExtra(RESULT_PATH_ARRAY, pathArr);
        setResult(RESULT_OK, resultData);
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
