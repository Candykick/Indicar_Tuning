package com.iindicar.indicar.view.community.boardWrite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.databinding.ActivityBoardWriteBinding;
import com.iindicar.indicar.service.BoardUploadService;
import com.iindicar.indicar.utils.ActivityResultEvent;
import com.iindicar.indicar.utils.BusProvider;
import com.iindicar.indicar.utils.CustomDialog;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.utils.KeyboardUtil;
import com.iindicar.indicar.view.BaseActivity;
import com.squareup.otto.Subscribe;

import static com.iindicar.indicar.view.community.boardWrite.WriteFilterFragment.REQUEST_CAMERA;

public class BoardWriteActivity extends BaseActivity<ActivityBoardWriteBinding> implements WriteFilterFragment.OnFilterFragmentInteractionListener, WriteContentFragment.OnContentFragmentInteractionListener {

    public static final String EXTRA_UPDATE_BOARD = "EXTRA_UPDATE_BOARD";

    public static final String RESULT_UPDATED = "RESULT_UPDATED";

    private boolean isUpdating = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_board_write;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 게시글 새로 작성하는지 수정하는지 확인
        isUpdating = getIntent().getBooleanExtra(EXTRA_UPDATE_BOARD, false);

        initView();
    }

    private void initView() {

        // 툴바 생성
        GlideUtil.loadImageResource(binding.toolbar.ivCenter, R.drawable.toolbar_write);
        GlideUtil.loadImageResource(binding.toolbar.btnLeft, R.drawable.toolbar_back);
        binding.toolbar.btnLeft.setOnClickListener(v -> onBackPressed());
        binding.toolbar.container.setBackground(null);
        setSupportActionBar(binding.toolbarLayout);

        // 필터 프래그먼트 생성
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WriteFilterFragment.newInstance(isUpdating))
                .commit();
    }

    @Override
    public void startBoardUploadService() {

        // 게시물 업로드 서비스 시작
        Intent intent = new Intent(getApplicationContext(), BoardUploadService.class);
        intent.putExtra(BoardUploadService.EXTRA_BOARD_UPDATE, isUpdating);
        startService(intent);

        if(isUpdating) {
            Intent result = new Intent();
            result.putExtra(RESULT_UPDATED, true);
            setResult(RESULT_OK, result);
        }

        finish();
    }

    @Override
    public void onBackPressed() {
        KeyboardUtil.closeKeyboard(this, new EditText(this));
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        BoardRepository.getInstance().setCacheWriteBoard(null);
        super.onDestroy();
    }

    /**
     * 다음으로 버튼 클릭 callback */
    @Override
    public void onStartContentFragment() {

        binding.appbar.setExpanded(false);

        // 프래그먼트 다음 페이지로 전환
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WriteContentFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Indicar Tuning","Activity onActivityResult");
        BusProvider.getInstance().post(ActivityResultEvent.create(requestCode, resultCode, data));
    }
}
