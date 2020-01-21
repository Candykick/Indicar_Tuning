package com.iindicar.indicar.view.shopping.shoppingHome;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderImageBannerItemBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

public class ImageListAdapter extends BaseRecyclerAdapter<String, ImageListAdapter.ViewHolder> {

    public ImageListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        GlideUtil.loadImage(holder.binding.image, itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_image_banner_item, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        HolderImageBannerItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
