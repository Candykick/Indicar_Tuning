package com.iindicar.indicar.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.DialogCustomBinding;

public class CustomDialog extends Dialog {

    private DialogCustomBinding binding;

    private Context context;
    private OnCancelButtonClickListener cancelButtonClickListener;
    private OnSubmitButtonClickListener submitButtonClickListener;

    private final int layoutId;

    public CustomDialog(@NonNull Context context, @NonNull int layoutId) {
        super(context);
        this.context = context;
        this.layoutId = layoutId;
    }

    public CustomDialog(@NonNull Context context, @NonNull int layoutId, OnSubmitButtonClickListener submitButtonClickListener) {
        super(context);
        this.context = context;
        this.layoutId = layoutId;
        this.submitButtonClickListener = submitButtonClickListener;
    }

    public CustomDialog(@NonNull Context context, @NonNull int layoutId, OnCancelButtonClickListener cancelButtonClickListener, OnSubmitButtonClickListener submitButtonClickListener) {
        super(context);
        this.context = context;
        this.layoutId = layoutId;
        this.cancelButtonClickListener = cancelButtonClickListener;
        this.submitButtonClickListener = submitButtonClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null, false);
        binding = DataBindingUtil.bind(contentView);

        // 뷰 그려서 프레임레이아웃에 등록
        View view = LayoutInflater.from(context).inflate(layoutId, null, false);
        binding.frame.addView(view);

        setContentView(binding.getRoot());

        // 다이얼로그 외부 화면 흐리게 및 크기 변경
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount = 0.6f;
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        // 취소 버튼 리스너 등록
        binding.btnCancel.setOnClickListener(v -> {
            // 취소버튼 리스너 따로 등록 안하면 기본으로 다이얼로그 끄기
            if(cancelButtonClickListener == null){
                dismiss();
            } else {
                cancelButtonClickListener.onCancelButtonClick();
            }
        });

        // 확인 버튼 리스너 등록
        binding.btnSubmit.setOnClickListener(v -> {
            if(submitButtonClickListener != null){
                submitButtonClickListener.onSubmitButtonClick();
                dismiss();
            }
        });
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return false;
    }

    public interface OnCancelButtonClickListener {

        void onCancelButtonClick();
    }

    public interface OnSubmitButtonClickListener {

        void onSubmitButtonClick();
    }
}
