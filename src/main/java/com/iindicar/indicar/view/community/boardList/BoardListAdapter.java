package com.iindicar.indicar.view.community.boardList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderBoardItemBinding;
import com.iindicar.indicar.model.Board;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

public class BoardListAdapter extends BaseRecyclerAdapter<Board, BoardListAdapter.BoardViewHolder> {

    public BoardListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(BoardViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_board_item, null, false);
        return new BoardViewHolder(view);
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {

        protected HolderBoardItemBinding binding;

        public BoardViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
