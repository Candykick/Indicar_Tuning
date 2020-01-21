package com.iindicar.indicar.view.community.boardWrite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.board.BoardRepository;
import com.iindicar.indicar.databinding.FragmentWriteContentBinding;
import com.iindicar.indicar.view.BaseFragment;

public class WriteContentFragment extends BaseFragment<FragmentWriteContentBinding> implements BoardWriteContract.ContentView {

    private OnContentFragmentInteractionListener interactionListener;

    private WriteContentPresenter presenter;
    private WriteContentAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_write_content;
    }

    public WriteContentFragment(){}

    public static WriteContentFragment newInstance(){
        return new WriteContentFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnContentFragmentInteractionListener){
            interactionListener = (OnContentFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnContentFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new WriteContentPresenter(this, BoardRepository.getInstance());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new WriteContentAdapter(context);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        // 글 작성 리사이클러 뷰 생성
        binding.recyclerContent.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerContent.setAdapter(adapter);

        // snap helper (아이템 시작점에 맞춰서 스크롤)
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerContent);

        // 등록하기 버튼 클릭 리스너 등록
        binding.btnNext.setOnClickListener(v -> {
            makeToast(getString(R.string.strWritingPost));
            interactionListener.startBoardUploadService();
        });
        binding.btnCancel.setOnClickListener(v -> getActivity().finish() );

        presenter.onViewCreated(); // 뷰 생성 후 호출

        // item 크기 만큼 캐싱하기
        // 일정 페이지 이상 넘어가면 EditText 초기화되는 현상 막기 위함
        binding.recyclerContent.setItemViewCacheSize(adapter.getItemCount());
    }

    @Override
    public void makeToast(String result) {
        Toast.makeText(context, "" + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return context;
    }

    /**
     * 액티비티와 통신하기 위한 콜백 리스너 */
    public interface OnContentFragmentInteractionListener {

        void startBoardUploadService();
    }
}
