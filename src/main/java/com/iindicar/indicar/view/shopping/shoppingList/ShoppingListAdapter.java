package com.iindicar.indicar.view.shopping.shoppingList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderShoppingBestItemBinding;
import com.iindicar.indicar.databinding.HolderShoppingItemBinding;
import com.iindicar.indicar.model.Product;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

import java.util.List;

public class ShoppingListAdapter extends BaseRecyclerAdapter<Product, RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HOME = 10;
    public static final int VIEW_TYPE_ALL = 11;
    private final int viewType;

    public ShoppingListAdapter(Context context, int viewType) {
        super(context);
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder holder, int position) {

        Product item = itemList.get(position);

        if(holder instanceof ViewHolder){ // 전체 상품

            ((ViewHolder) holder).binding.setItem(item);

            // 원가 텍스트에 줄긋기
            TextView tvOriginalPrice = ((ViewHolder) holder).binding.tvOriginalPrice;
            tvOriginalPrice.setPaintFlags(tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            if("Y".equals(itemList.get(position).getIsSoldout())){
                ((ViewHolder) holder).binding.ivSoldOut.setVisibility(View.VISIBLE);
            }

            if("Y".equals(itemList.get(position).getIsBest())){
                ((ViewHolder) holder).binding.ivBest.setVisibility(View.VISIBLE);
            }

        } else if(holder instanceof HomeViewHolder){ // 홈 리스트 상품

            ((HomeViewHolder) holder).binding.setItem(item);

            // 원가 텍스트에 줄긋기
            TextView tvOriginalPrice = ((HomeViewHolder) holder).binding.tvOriginalPrice;
            tvOriginalPrice.setPaintFlags(tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            if("Y".equals(itemList.get(position).getIsSoldout())){
                ((HomeViewHolder) holder).binding.ivSoldOut.setVisibility(View.VISIBLE);
            }

            if("Y".equals(itemList.get(position).getIsBest())){
                ((HomeViewHolder) holder).binding.ivBest.setVisibility(View.VISIBLE);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_HOME) {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_shopping_best_item, null, false);
            return new HomeViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.holder_shopping_item, null, false);
            return new ViewHolder(view);
        }
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        protected HolderShoppingBestItemBinding binding;

        public HomeViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected HolderShoppingItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
