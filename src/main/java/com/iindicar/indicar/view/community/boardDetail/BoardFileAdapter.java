package com.iindicar.indicar.view.community.boardDetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderFileItemBinding;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

public class BoardFileAdapter extends BaseRecyclerAdapter<BoardFile, BoardFileAdapter.FileViewHolder> {

    public BoardFileAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(FileViewHolder holder, int position) {

        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_file_item, parent, false);
        return new FileViewHolder(view);
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {

        protected HolderFileItemBinding binding;

        public FileViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
