package com.iindicar.indicar.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iindicar.indicar.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BindingUtil {

    @BindingConversion
    public static int setVisibility(boolean isVisible){

        return isVisible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter({"resourceId"})
    public static void resourceId(ImageButton imageButton, int resourceId){

        imageButton.setImageResource(resourceId);
    }

    @BindingAdapter({"selected"})
    public static void setSelected(View view, boolean selected){
        view.setSelected(selected);
    }

    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing){
        refreshLayout.setRefreshing(refreshing);
    }

    @BindingAdapter({"setFilter"})
    public static void setBoardFilter(Button view, boolean selected){
        if(selected) {
            view.setBackgroundResource(R.drawable.line_round_box);
            view.setTextColor(view.getContext().getResources().getColor(R.color.white));
        } else {
            view.setBackgroundResource(R.drawable.line_round);
            view.setTextColor(view.getContext().getResources().getColor(R.color.gray));
        }
    }

    @BindingAdapter({"price"})
    public static void convertTextToDisplayPrice(TextView textView, String inputText) {
        DecimalFormat priceFormat = new DecimalFormat("###,###");
        textView.setText(priceFormat.format(Long.parseLong(inputText)));
    }

    @BindingAdapter({"date"})
    public static void convertDateToDisplayText(TextView textView, String inputDate) {

        if(inputDate == null){
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateYearOnly = new SimpleDateFormat("yyyy.MM.dd");

        Date input = null;
        try {
            input = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String displayString = "";

        long diffTimeMillis = System.currentTimeMillis() - input.getTime(); // 경과된 시간 (ms)
        int diffTime = (int) (diffTimeMillis / (1000 * 60)); // 분 단위로 변경

        if (diffTime >= 0) {
            if (diffTime < 60) { // ~ 59분 전
                displayString = diffTime + "분 전";
            } else {
                diffTime = diffTime / 60; // 시간 단위로 변경

                if (diffTime < 24) { // ~ 23시간 전
                    displayString = diffTime + "시간 전";
                } else { // 날짜 출력
                    displayString = dateYearOnly.format(input);
                }
            }
        }
        textView.setText(displayString);
    }
}
