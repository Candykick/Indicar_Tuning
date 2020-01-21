package com.iindicar.indicar.view.notice.noticeList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.model.Notice;
import com.iindicar.indicar.utils.App;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;
import com.iindicar.indicar.databinding.HolderNoticeItemBinding;
import com.iindicar.indicar.databinding.HolderNoticeListItemBinding;

public class NoticeListAdapter extends BaseRecyclerAdapter<Notice, RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_MAIN = 10;
    public static final int VIEW_TYPE_LIST = 11;

    private final int viewType;

    public NoticeListAdapter(Context context, int viewType) {
        super(context);
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolder) {
            ((ViewHolder)holder).binding.setItem(itemList.get(position));
        } else if (holder instanceof ListViewHolder){
            ((ListViewHolder) holder).binding.setItem(itemList.get(position));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_MAIN) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_notice_item, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_notice_list_item, parent, false);
            return new ListViewHolder(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected HolderNoticeItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        protected HolderNoticeListItemBinding binding;

        public ListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
