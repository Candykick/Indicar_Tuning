package com.iindicar.indicar.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseMultiViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<BaseDataBinder> binderList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).newViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int binderPosition = getBinderPosition(position);
        getDataBinder(viewHolder.getItemViewType()).bindViewHolder(viewHolder, binderPosition);
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for(BaseDataBinder binder : binderList){
            itemCount += binder.getItemCount();
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = 0;
        for (BaseDataBinder binder : binderList) {
            itemCount += binder.getItemCount();
            if (position < itemCount) {
                return binderList.indexOf(binder);
            }
        }
        throw new IllegalArgumentException("arg position is invalid");
    }

    public <T extends BaseDataBinder> T getDataBinder(int viewType) {
        return (T) binderList.get(viewType);
    }

    public int getPosition(BaseDataBinder binder, int binderPosition) {
        int viewType = binderList.indexOf(binder);
        if(viewType < 0){
            throw new IllegalStateException("binder does not exist in adapter");
        }

        int position = binderPosition;
        for(int i = 0 ; i < viewType ; i++){
            position += binderList.get(i).getItemCount();
        }

        return position;
    }

    public int getBinderPosition(int position) {
        for (BaseDataBinder binder : binderList) {
            int binderItemCount = binder.getItemCount();
            if (position - binderItemCount < 0) {
                break;
            }
            position -= binderItemCount;
        }
        return position;
    }

    public void notifyBinderItemChanged(BaseDataBinder binder, int binderPosition) {
        notifyItemChanged(getPosition(binder, binderPosition));
    }

    public void notifyBinderItemInserted(BaseDataBinder binder, int binderPosition) {
        notifyItemInserted(getPosition(binder, binderPosition));
    }

    public void notifyBinderItemMoved(BaseDataBinder binder, int fromPosition, int toPosition) {
        notifyItemMoved(getPosition(binder, fromPosition), getPosition(binder, toPosition));
    }

    public void notifyBinderItemRemoved(BaseDataBinder binder, int binderPosition) {
        notifyItemRemoved(getPosition(binder, binderPosition));
    }

    public List<BaseDataBinder> getBinderList() {
        return binderList;
    }

    public void addBinder(BaseDataBinder binder) {
        binderList.add(binder);
    }

    public void addAllBinder(List<BaseDataBinder> dataSet) {
        binderList.addAll(dataSet);
    }

    public void addAllBinder(BaseDataBinder... dataSet) {
        binderList.addAll(Arrays.asList(dataSet));
    }
}
