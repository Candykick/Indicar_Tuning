package com.iindicar.indicar.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.data.source.files.FileRepository;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.BoardWrite;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.community.boardDetail.BoardDetailActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 게시글을 등록하는 서비스
 * BoardWriteActivity 에서 호출되며 백그라운드에서 실행 (앱 종료해도 실행됨)
 * 업로드 완료되면 자동 소멸 */
public class BoardUploadService extends Service {

    public final String TAG = getClass().getSimpleName();

    public static final String EXTRA_BOARD_UPDATE = "EXTRA_BOARD_UPDATE";

    FileRepository fileRepository;
    BoardRepository boardRepository;

    private boolean isUpdating;
    private BoardWrite boardWrite;

    @Override
    public void onCreate() {
        super.onCreate();
        fileRepository = FileRepository.getInstance();
        boardRepository = BoardRepository.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boardWrite = boardRepository.getCacheWriteBoard();
        isUpdating = intent.getBooleanExtra(EXTRA_BOARD_UPDATE, false);

        if(!isUpdating){
            startUpload(); // 글 작성
        } else {
            startUpdate(); // 글 수정
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("CheckResult")
    private void startUpload() {

        // insertFiles 결과 Observable 을 구독한다
        fileRepository.insertFiles(boardWrite.getFileList())
                .subscribe(resultArr -> {

                    // 게시물 업로드 요청 보냄
                    boardRepository.insertData(RequestMapper.boardWriteMapping(boardWrite, LocaleHelper.getLanguage(this)))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(result -> {
                                        Toast.makeText(this,
                                                getResources().getString(R.string.board_upload_success),
                                                Toast.LENGTH_SHORT).show();

                                        stopSelf();
                                    },
                                    error -> {
                                        Toast.makeText(this,
                                                getResources().getString(R.string.board_upload_fail),
                                                Toast.LENGTH_SHORT).show();

                                        stopSelf();
                                    });

                }, error -> stopSelf());
    }

    @SuppressLint("CheckResult")
    private void startUpdate() {

        // insertFiles 결과 Observable 을 구독한다
        fileRepository.updateFiles(boardWrite.getFileList())
                .subscribe(resultArr -> {

                    boardRepository.updateData(RequestMapper.boardUpdateMapping(boardWrite, LocaleHelper.getLanguage(this)))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(result -> {
                                        Toast.makeText(getApplicationContext(),
                                                getResources().getString(R.string.board_update_success),
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), BoardDetailActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                                Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra(BoardDetailActivity.EXTRA_BOARD_UPDATE, true);
                                        startActivity(intent);

                                        stopSelf();
                                    },
                                    error -> {
                                        Toast.makeText(getApplicationContext(),
                                                getResources().getString(R.string.board_update_fail),
                                                Toast.LENGTH_SHORT).show();
                                        stopSelf();
                                    });
                }, error -> stopSelf());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
