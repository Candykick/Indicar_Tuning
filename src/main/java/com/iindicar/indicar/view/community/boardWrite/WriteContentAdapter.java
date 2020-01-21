package com.iindicar.indicar.view.community.boardWrite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderWriteContentBinding;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

public class WriteContentAdapter extends BaseRecyclerAdapter<BoardFile, WriteContentAdapter.WriteViewHolder> {

    public WriteContentAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(final WriteViewHolder holder, final int position) {
        final BoardFile item = itemList.get(position);

        holder.binding.setItem(item);

        holder.binding.etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setFileText(holder.binding.etText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(item.getUploadFlag() == BoardFile.FLAG_NULL){
                    item.setUploadFlag(BoardFile.FLAG_UPDATE);
                }
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_write_content, parent, false);
        return new WriteViewHolder(view);
    }

    public class WriteViewHolder extends RecyclerView.ViewHolder{

        protected HolderWriteContentBinding binding;

        public WriteViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
