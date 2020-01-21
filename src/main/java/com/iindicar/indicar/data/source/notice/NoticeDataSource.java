package com.iindicar.indicar.data.source.notice;

import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.source.LoadData;
import com.iindicar.indicar.data.source.LoadDataList;
import com.iindicar.indicar.model.Notice;

public interface NoticeDataSource extends LoadDataList<BoardListRequest, Notice>,
        LoadData<BoardDetailRequest, Notice> {

}