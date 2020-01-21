package com.iindicar.indicar.data.source.files;

import android.util.Log;

import com.iindicar.indicar.data.remote.LoadDataResponse;
import com.iindicar.indicar.data.remote.RetrofitApi;
import com.iindicar.indicar.data.remote.RetrofitClient;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.model.BoardFile;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FileRemoteDataSource implements FileDataSource {

    private static final String TAG = "FileRemoteDataSource";

    private static FileRemoteDataSource fileRemoteDataSource;

    private FileRemoteDataSource() {}

    public static FileRemoteDataSource getInstance(){
        if(fileRemoteDataSource == null){
            fileRemoteDataSource = new FileRemoteDataSource();
        }
        return fileRemoteDataSource;
    }

    /**
     * 파일을 서버에 전송한다.
     * fileList 의 item 하나씩 서버에 요청을 보낸 후 (순서 보장하기 위해 concatMap() 함수 사용)
     * 응답으로 오는 fileId 를 List 에 담아서 반환한다. */
    @Override
    public Single<List<String>> insertFiles(List<BoardFile> fileList) {

        return Observable
                .fromArray(fileList.toArray())
                .concatMap(file ->
                        //** 비동기 요청에서 file 이 입력된 순서를 보장하기 위해 concatMap() 함수 사용
                        // HTTP 요청을 위해 newThread() 호출
                        // insertFiles 의 응답으로 온 Observable 객체를 반환한다
                        RetrofitClient.getClient().create(RetrofitApi.class)
                                .insertFiles(RequestMapper.insertFileMapping((BoardFile) file))
                                .subscribeOn(Schedulers.newThread())
                                .doOnNext(result -> ((BoardFile) file).setFileId(result.getContent()))
                                .map(LoadDataResponse::getContent)
                )
                .toList(); // 반환된 Observable 들을 List 로 변환
    }

    /**
     * fileList 의 item 하나씩 읽어서 insert / update / null 판단 후
     * 해당하는 요청을 서버에 보내고 (순서 보장하기 위해 concatMap() 함수 사용)
     * 응답으로 오는 fileId 를 List 에 담아서 반환한다. */
    @Override
    public Single<List<String>> updateFiles(List<BoardFile> fileList) {

        return Observable
                .fromArray(fileList.toArray())
                .concatMap(file -> {
                    // file item 의 FLAG 값을 읽어서 insert / update / null 확인 후
                    // 각각에 해당하는 api 요청 보냄
                    //** 순서 보장하기 위해 concatMap() 사용
                    switch (((BoardFile) file).getUploadFlag()){
                        case BoardFile.FLAG_INSERT:
                            return RetrofitClient.getClient().create(RetrofitApi.class)
                                    .insertFiles(RequestMapper.insertFileMapping((BoardFile) file))
                                    .subscribeOn(Schedulers.newThread())
                                    .doOnNext(response -> ((BoardFile) file).setFileId(response.getContent()))
                                    .map(LoadDataResponse::getContent);
                        case BoardFile.FLAG_UPDATE:
                            return RetrofitClient.getClient().create(RetrofitApi.class)
                                    .updateFile(RequestMapper.updateFileMapping((BoardFile) file))
                                    .subscribeOn(Schedulers.newThread())
                                    .map(response -> ((BoardFile) file).getFileId());
                        default:
                            return Observable.just(((BoardFile) file).getFileId());
                    }
                })
                .toList(); // 반환된 Observable 들을 List 로 변환
    }
}
