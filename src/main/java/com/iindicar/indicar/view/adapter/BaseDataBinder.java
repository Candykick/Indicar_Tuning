package com.iindicar.indicar.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.view.listener.OnItemClickListener;
import com.iindicar.indicar.view.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataBinder<T, H extends RecyclerView.ViewHolder> {

    protected BaseMultiViewAdapter mDataBindAdapter;
    protected List<T> itemList = new ArrayList<>();
    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public BaseDataBinder(BaseMultiViewAdapter dataBindAdapter) {
        mDataBindAdapter = dataBindAdapter;
    }

    abstract public H newViewHolder(ViewGroup parent);

    abstract public void onBindView(H holder, int position);

    public void bindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if(onItemLongClickListener != null){
                    onItemLongClickListener.onItemLongClick(position);
                }

                return false;
            }
        });

        onBindView((H) holder, position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public int getItemCount() {
        if(itemList == null){
            return 0;
        }
        return this.itemList.size();
    }

    public T getItem(int position){
        if(itemList == null){
            return null;
        }
        return this.itemList.get(position);
    }

    public List<T> getItemList(){
        if(itemList == null){
            return null;
        }
        return this.itemList;
    }

    public void addItems(List<T> items){
        if (this.itemList == null) {
            this.itemList = items;
            mDataBindAdapter.notifyDataSetChanged();
        } else {
            int position = this.itemList.size();
            this.itemList.addAll(items);
            mDataBindAdapter.notifyDataSetChanged();
        }
    }

    public void addItems(int position, List<T> items){
        if(this.itemList == null){
            this.itemList = new ArrayList<>();
        }
        if(position > this.itemList.size()){
            return;
        }
        this.itemList.addAll(position, items);
        mDataBindAdapter.notifyDataSetChanged();
    }

    public void addItem(T item){
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
            itemList.add(item);
            mDataBindAdapter.notifyDataSetChanged();
        } else {
            int position = this.itemList.size();
            this.itemList.add(item);
            mDataBindAdapter.notifyDataSetChanged();
        }
    }

    public void addItem(int position, T item){
        if(this.itemList == null){
            return;
        }
        if(position > this.itemList.size()){
            return;
        }
        this.itemList.add(position, item);
        mDataBindAdapter.notifyDataSetChanged();
    }

    public void updateItems(List<T> items){
        if(this.itemList == null){
            itemList = new ArrayList<>();
        }
        this.itemList.clear();
        this.itemList.addAll(items);

        mDataBindAdapter.notifyDataSetChanged();
    }

    public void updateItem(int position, T item){
        if(this.itemList == null){
            return;
        }
        if(position > this.itemList.size()){
            return;
        }
        this.itemList.remove(position);
        this.itemList.add(position, item);

        mDataBindAdapter.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if (this.itemList != null && position < this.itemList.size()) {
            this.itemList.remove(position);
            mDataBindAdapter.notifyDataSetChanged();
        }
    }

    public void clearItems(){
        if(itemList != null){
            itemList.clear();
            mDataBindAdapter.notifyDataSetChanged();
        }
    }
}