package com.iindicar.indicar.data.source.notice;

import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.model.Notice;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 1. 공지사항 리스트 조회
 *
 * */
public class NoticeRepository implements NoticeDataSource {

    private static NoticeRepository noticeRepository;

    private NoticeRemoteDataSource remoteDataSource;

    private NoticeRepository() {
        remoteDataSource = NoticeRemoteDataSource.getInstance();
    }

    public static NoticeRepository getInstance() {
        if(noticeRepository == null){
            noticeRepository = new NoticeRepository();
        }
        return noticeRepository;
    }

    private List<Notice> cacheList = new ArrayList<>();
    private boolean isCacheDirty = true;

    @Override
    public Observable<List<Notice>> loadDataList(BoardListRequest source) {

        if(isCacheDirty || cacheList.size() == 0){
            // 캐시 변동사항 있거나 캐시리스트가 없으면 서버에 요청
            return remoteDataSource.loadDataList(source)
                    .doOnNext(list -> {
                        cacheList = list;
                        isCacheDirty = false;
                    });
        } else {
            return Observable.just(cacheList);
        }
    }

    @Override
    public Observable<Notice> loadData(BoardDetailRequest source) {
        return remoteDataSource.loadData(source);
    }
}
