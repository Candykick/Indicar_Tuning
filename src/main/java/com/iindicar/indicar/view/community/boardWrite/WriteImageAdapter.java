package com.iindicar.indicar.view.community.boardWrite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderPickedImageBinding;
import com.iindicar.indicar.model.BoardFile;
import com.iindicar.indicar.view.adapter.AdapterContract;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

/**
 * 선택된 사진 리스트 보여줄 어댑터 */
public class WriteImageAdapter extends BaseRecyclerAdapter<BoardFile, WriteImageAdapter.ImageViewHolder> implements AdapterContract.View, AdapterContract.Model<BoardFile> {

    public WriteImageAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ImageViewHolder holder, final int position) {

        holder.binding.setItem(itemList.get(position));

        holder.binding.btnRemove.setOnClickListener(v -> this.removeItem(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_picked_image, parent, false);
        return new ImageViewHolder(view);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        protected HolderPickedImageBinding binding;

        public ImageViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
