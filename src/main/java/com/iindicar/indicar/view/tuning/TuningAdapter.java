package com.iindicar.indicar.view.tuning;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.HolderCarListItemBinding;
import com.iindicar.indicar.model.Car;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TuningAdapter extends BaseRecyclerAdapter<Car, TuningAdapter.ViewHolder>  implements Filterable {

    private List<Car> itemAllList;
    private Filter searchFilter;

    public TuningAdapter(Context context) {
        super(context);
    }

    public void setItemAllList(List<Car> itemAllList) {
        this.itemAllList = itemAllList;
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_car_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public Filter getFilter() {
        if(searchFilter == null){
            searchFilter = new searchFilter();
        }
        return searchFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        protected HolderCarListItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class searchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length() == 0){
                // 검색어 입력 안된 경우 전체보기
                results.values = itemAllList;
                results.count = itemAllList.size();

            } else {
                // 검색어 입력된 경우
                final String searchKey = constraint.toString().toUpperCase();

                ArrayList<Car> resultList = new ArrayList<>();
                int count = itemAllList.size();
                for(int i = 0 ; i < count ; i++){
                    final Car item = itemAllList.get(i);
                    if(item.getCarName().toUpperCase().contains(searchKey)
                            || item.getCompany().toUpperCase().contains(searchKey)
                            || item.getCarNameKor().toUpperCase().contains(searchKey)
                            || item.getCompanyKor().toUpperCase().contains(searchKey)){
                        resultList.add(item);
                    }
                }
                results.values = resultList;
                results.count = resultList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // 검색 결과를 리스트에 등록
            itemList = (List<Car>) results.values;

            // 어댑터에게 변경사항 알리기
            notifyDataSetChanged();
        }
    }
}
