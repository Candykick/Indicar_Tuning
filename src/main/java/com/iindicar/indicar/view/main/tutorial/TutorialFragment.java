package com.iindicar.indicar.view.main.tutorial;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.FragmentTutorialBinding;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseFragment;

public class TutorialFragment extends BaseFragment<FragmentTutorialBinding> {

    private static final String EXTRA_PAGE_INDEX = "EXTRA_PAGE_INDEX";

    public final ObservableField<String> tabName = new ObservableField<>();
    public final ObservableField<String> mainText = new ObservableField<>();
    public final ObservableField<String> subText = new ObservableField<>();

    private static final int[] TAB_IMAGE_ID = {
            R.drawable.toolbar_tuning,
            R.drawable.toolbar_tuning,
            R.drawable.toolbar_community
    };

    private static final int[] TAB_NAME_ID = {
            R.string.welcome_tab_tuning,
            R.string.welcome_tab_tuning,
            R.string.welcome_tab_community
    };

    private static final int[] MAIN_TEXT_ID = {
            R.string.welcome_main_text_tuning,
            R.string.welcome_main_text_unity,
            R.string.welcome_main_text_community
    };

    private static final int[] SUB_TEXT_ID = {
            R.string.welcome_sub_text_tuning,
            R.string.welcome_sub_text_unity,
            R.string.welcome_sub_text_community
    };

    private int pageIndex;

    public TutorialFragment(){}

    public static TutorialFragment newInstance(int index) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_PAGE_INDEX, index);

        TutorialFragment fragment = new TutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tutorial;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageIndex = getArguments().getInt(EXTRA_PAGE_INDEX);

        tabName.set(getString(TAB_NAME_ID[pageIndex]));
        mainText.set(getString(MAIN_TEXT_ID[pageIndex]));
        subText.set(getString(SUB_TEXT_ID[pageIndex]));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setFragment(this);

        GlideUtil.loadImageResource(binding.imageTab, TAB_IMAGE_ID[pageIndex]);

        switch (pageIndex){
            case 0:
                GlideUtil.loadImageResource(binding.imageGif, R.drawable.tutorial1);
                //GlideUtil.loadGif(binding.imageGif, R.drawable.tutorial1);
                break;
            case 1:
                GlideUtil.loadImageResource(binding.imageGif, R.drawable.tutorial2);
                //GlideUtil.loadGif(binding.imageGif, R.drawable.tutorial2);
                break;
            case 2:
                GlideUtil.loadImageResource(binding.imageGif, R.drawable.tutorial3);
                //GlideUtil.loadGif(binding.imageGif, R.drawable.tutorial2);
                break;
        }
    }
}
