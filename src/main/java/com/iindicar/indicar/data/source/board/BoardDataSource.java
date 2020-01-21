package com.iindicar.indicar.data.source.board;

import com.iindicar.indicar.data.remote.BoardDeleteRequest;
import com.iindicar.indicar.data.remote.BoardDetailRequest;
import com.iindicar.indicar.data.remote.BoardListRequest;
import com.iindicar.indicar.data.remote.BoardWriteRequest;
import com.iindicar.indicar.data.source.DeleteData;
import com.iindicar.indicar.data.source.InsertData;
import com.iindicar.indicar.data.source.LoadData;
import com.iindicar.indicar.data.source.LoadDataList;
import com.iindicar.indicar.data.source.UpdateData;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.model.BoardWrite;

import java.util.List;

import io.reactivex.Observable;

public interface BoardDataSource extends

        LoadDataList<BoardListRequest, Board>,

        LoadData<BoardDetailRequest, Board>,

        InsertData<BoardWriteRequest>,

        DeleteData<BoardDeleteRequest>,

        UpdateData<BoardWriteRequest> {

    BoardWrite getCacheWriteBoard();

    void setCacheWriteBoard(BoardWrite cacheWriteBoard);

    Observable<List<Board>> loadDataList(int dataType, BoardListRequest source);

    Observable<String> setBoardLike(BoardDetailRequest source);
}