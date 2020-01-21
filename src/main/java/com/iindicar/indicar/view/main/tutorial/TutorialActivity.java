package com.iindicar.indicar.view.main.tutorial;

import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.ActivityTutorialBinding;
import com.iindicar.indicar.view.BaseActivity;

public class TutorialActivity extends BaseActivity<ActivityTutorialBinding> {

    private final int NUM_OF_PAGES = 3;
    public final ObservableInt currentPage = new ObservableInt();

    private WelcomePagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tutorial;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 팝업창 크기 변경
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        // 뷰와 액티비티 바인딩
        binding.setActivity(this);

        initView();
    }

    /**
     * 외부영역 클릭시 종료 안되게 막기
     * @return true : 하단 뷰에 이벤트 전달 / false : 전달 안함 */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    private void initView() {

        // 어댑터 생성
        adapter = new WelcomePagerAdapter(getSupportFragmentManager());

        // 뷰페이저 생성
        binding.viewPagerWelcome.setAdapter(adapter);
        binding.viewPagerWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                currentPage.set(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        // 다음으로, 시작하기 버튼 리스너 등록
        binding.btnNext.setOnClickListener(v -> {
            if(currentPage.get() < (NUM_OF_PAGES - 1)) {
                // 마지막 페이지 아니면 다음페이지로 스크롤
                binding.viewPagerWelcome.setCurrentItem(currentPage.get() + 1, true);
            } else {
                // 마지막 페이지인 경우 종료
                finish();
            }
        });

        // 건너뛰기 버튼 리스너 등록
        binding.btnSkip.setOnClickListener(v -> finish());
    }

    /**
     * 튜토리얼 페이지 어댑터 */
    private class WelcomePagerAdapter extends FragmentStatePagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return TutorialFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_OF_PAGES;
        }
    }
}
