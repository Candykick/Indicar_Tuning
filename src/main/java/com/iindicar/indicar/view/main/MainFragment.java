package com.iindicar.indicar.view.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.LinearLayout;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.notice.NoticeRepository;
import com.iindicar.indicar.databinding.FragmentMainBinding;
import com.iindicar.indicar.mapper.RequestMapper;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.listener.OnMenuClickListener;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;
import com.iindicar.indicar.view.notice.noticeDetail.NoticeDetailActivity;
import com.iindicar.indicar.view.notice.noticeList.NoticeListActivity;
import com.iindicar.indicar.view.notice.noticeList.NoticeListAdapter;
import com.iindicar.indicar.view.tuning.TuningActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainFragment extends BaseFragment<FragmentMainBinding> implements OnPageSelectedListener{

    private OnMenuClickListener drawerButtonClickListener;

    private LinearLayoutManager layoutManager;
    private NoticeListAdapter adapter;

    public MainFragment(){}

    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMenuClickListener){
            drawerButtonClickListener = (OnMenuClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFilterFragmentInteractionListener");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new NoticeListAdapter(context, NoticeListAdapter.VIEW_TYPE_MAIN);
        adapter.setOnItemClickListener(position -> startNoticeDetailActivity(adapter.getItem(position).getBoardId()));

        initView();

        // 공지사항 목록 요청
        NoticeRepository.getInstance().loadDataList(RequestMapper.noticeListMapping(1, 15, LocaleHelper.getLanguage(view.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notices -> {
                            adapter.addItems(notices);
                            binding.recyclerNotice.setItemViewCacheSize(adapter.getItemCount());
                        },
                        error -> {});
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {

        // 툴바 리스너 등록
        binding.toolbar.ivCenter.setImageResource(R.drawable.toolbar_tuning);
        binding.toolbar.btnLeft.setImageResource(R.drawable.toolbar_menu);
        binding.toolbar.btnLeft.setOnClickListener((v) -> drawerButtonClickListener.onDrawerButtonClicked());

        // 가상튜닝 버튼 리스너 등록
        //GlideUtil.loadImageResource(binding.imageTuning, R.drawable.btn_goto_tuning);
        GlideUtil.loadFutureImageDrawable(binding.imageTuning, resources.getDrawable(R.drawable.btn_goto_tuning));
        binding.imageTuning.setOnClickListener(v -> startTuningActivity());

        // 공지사항 리사이클러뷰
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.recyclerNotice.setLayoutManager(layoutManager);
        binding.recyclerNotice.setAdapter(adapter);
        binding.recyclerNotice.setNestedScrollingEnabled(false);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        drawerButtonClickListener = null;
    }

    public void startNoticeDetailActivity(String noticeId){
        if(LocaleHelper.getLanguage(context).equals("ko")) {
            Intent intent = new Intent(context, NoticeDetailActivity.class);
            intent.putExtra(NoticeDetailActivity.EXTRA_NOTICE_ID, noticeId);
            startActivity(intent);
        }
    }

    @Override
    public Context getContext() {
        return context;
    }

    /**
     * 가상튜닝 버튼 클릭 콜백 */
    private void startTuningActivity(){
        Intent intent = new Intent(context, TuningActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPageSelected() {
        // TODO.
    }
}
