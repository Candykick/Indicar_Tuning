package com.iindicar.indicar.data.source.notice;

import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.mapper.BoardMapper;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.model.Notice;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NoticeRemoteDataSource implements NoticeDataSource {

    private static NoticeRemoteDataSource noticeRemoteRepository;

    public static NoticeRemoteDataSource getInstance() {

        if (noticeRemoteRepository == null) {
            noticeRemoteRepository = new NoticeRemoteDataSource();
        }
        return noticeRemoteRepository;
    }

    private NoticeRemoteDataSource() { }

    /**
     * 공지사항 리스트 조회 api 요청 후 Observable 반환
     * File Url 앞에 "http://" 붙어있는지 체크해야됨 */
    @Override
    public Observable<List<Notice>> loadDataList(BoardListRequest source) {

        // 공지사항 리스트 조회 api 요청
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getNotices(source)
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                            // Url 앞에 "http://" 붙어있는지 체크 후 리턴
                            List<Notice> noticeList = response.getContent();
                            for(Notice notice : noticeList){
                                String url = BoardMapper.checkImageUrl(notice.getMainFileUrl());
                                notice.setMainFileUrl(url);
                            }
                            return noticeList;
                        }
                );
    }

    @Override
    public Observable<Notice> loadData(BoardDetailRequest source) {
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getNotice(source)
                .subscribeOn(Schedulers.newThread())
                .map(response -> {
                    // 결과 받아서 필요한 연산 수행
                    List<Notice> list = response.getContent();

                    // response body 에서 file 리스트를 가져옴
                    List<BoardFile> fileList = list.get(1).getFileList();
                    for(BoardFile file : fileList){
                        // url 앞에 "http://" 붙어있는지 체크
                        String url = BoardMapper.checkImageUrl(file.getFilePath());
                        file.setFilePath(url);
                    }

                    // 리스트의 첫번째 객체가 결과
                    Notice result = list.get(0);
                    // file 리스트 저장
                    result.setFileList(fileList);

                    return result;
                });
    }
}