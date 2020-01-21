package com.iindicar.indicar.view.community.comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderCommentItemBinding;
import com.iindicar.indicar.model.Comment;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

/**
 * */
public class CommentAdapter extends BaseRecyclerAdapter<Comment, CommentAdapter.CommentViewHolder>{

    public CommentAdapter(Context context) {
        super(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onBindView(CommentViewHolder holder, int position) {

        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        protected HolderCommentItemBinding binding;

        public CommentViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
