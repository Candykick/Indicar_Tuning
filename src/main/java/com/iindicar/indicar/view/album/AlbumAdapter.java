package com.iindicar.indicar.view.album;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.local.GalleryVO;
import com.iindicar.indicar.databinding.HolderAlbumItemBinding;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

public class AlbumAdapter extends BaseRecyclerAdapter<GalleryVO, AlbumAdapter.AlbumViewHolder> {

    public AlbumAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(AlbumViewHolder holder, int position) {
        WindowManager w = ((Activity)context).getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);

        holder.binding.imageView.getLayoutParams().height = metrics.widthPixels / 4;
        holder.binding.imageView.requestLayout();
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_album_item, parent, false);
        return new AlbumViewHolder(view);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        protected HolderAlbumItemBinding binding;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
