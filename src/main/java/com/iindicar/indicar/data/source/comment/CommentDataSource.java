package com.iindicar.indicar.data.source.comment;

import com.iindicar.indicar.data.remote.CommentListRequest;
import com.iindicar.indicar.data.remote.CommentWriteRequest;
import com.iindicar.indicar.data.source.DeleteData;
import com.iindicar.indicar.data.source.InsertData;
import com.iindicar.indicar.data.source.LoadDataList;
import com.iindicar.indicar.data.source.UpdateData;
import com.iindicar.indicar.model.Comment;

import io.reactivex.Observable;

public interface CommentDataSource extends

        LoadDataList<CommentListRequest, Comment>,

        InsertData<CommentWriteRequest>,

        DeleteData<CommentWriteRequest>,

        UpdateData<CommentWriteRequest> { }
