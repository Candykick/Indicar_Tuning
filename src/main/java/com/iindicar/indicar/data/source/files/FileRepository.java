package com.iindicar.indicar.data.source.files;

import com.iindicar.indicar.model.BoardFile;

import java.util.List;

import io.reactivex.Single;

/**
 * 1. 파일 상세 정보 조회
 * 2. 작성한 파일 (이미지 + 텍스트) 등록
 *
 * */
public class FileRepository implements FileDataSource {

    private static FileRepository fileRepository;
    private FileRemoteDataSource remoteDataSource;

    private FileRepository() {
        remoteDataSource = FileRemoteDataSource.getInstance();
    }

    public static FileRepository getInstance(){
        if(fileRepository == null){
            fileRepository = new FileRepository();
        }
        return fileRepository;
    }

    @Override
    public Single<List<String>> insertFiles(List<BoardFile> fileList) {

        return remoteDataSource.insertFiles(fileList);
    }

    @Override
    public Single<List<String>> updateFiles(List<BoardFile> fileList) {

        return remoteDataSource.updateFiles(fileList);
    }
}
