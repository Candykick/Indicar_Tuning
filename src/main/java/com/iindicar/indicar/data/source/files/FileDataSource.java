package com.iindicar.indicar.data.source.files;

import com.iindicar.indicar.model.BoardFile;

import java.util.List;

import io.reactivex.Single;

public interface FileDataSource {

    Single<List<String>> insertFiles(List<BoardFile> fileList);

    Single<List<String>> updateFiles(List<BoardFile> fileList);
}
