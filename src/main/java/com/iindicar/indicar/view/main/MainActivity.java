package com.iindicar.indicar.view.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.ActivityMainBinding;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.view.account.AccountFragment;
import com.iindicar.indicar.view.community.CommunityFragment;
import com.iindicar.indicar.view.listener.OnMenuClickListener;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;
import com.iindicar.indicar.view.shopping.ShoppingEngFragment;
import com.iindicar.indicar.view.shopping.ShoppingFragment;

import java.security.MessageDigest;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements OnMenuClickListener, TabLayout.OnTabSelectedListener{

    //뒤로가기 버튼을 두 번 클릭시 종료. 이를 구현하기 위한 변수
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressTime = 0;

    // 메인 탭 버튼 이미지
    private final int tabImages[] = {
            R.drawable.tab_main_tuning,
            R.drawable.tab_main_community,
            R.drawable.tab_main_shopping,
            R.drawable.tab_main_account
    };
    private final int PADDING_SMALL = 30;
    private final int PADDING_ULTRA_SMALL = 24;
    private final float ALPHA_SELECTED = 0.8f;
    private final float ALPHA_UNSELECTED = 0.5f;

    private SharedPreferences prefLogin;

    private MainPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefLogin = getApplication().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

        //키해쉬 받아오기
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.iindicar.indicar", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //푸시알람을 받아오는 부분을 구현한다.
        if (prefLogin.getBoolean("EventAlarm", true) == true) {
            FirebaseMessaging.getInstance().subscribeToTopic("Event");
            FirebaseInstanceId.getInstance().getToken();
        }
        if (prefLogin.getBoolean("OtherAlarm", true) == true) {
            FirebaseMessaging.getInstance().subscribeToTopic("Other");
            FirebaseInstanceId.getInstance().getToken();
        }

        // 유저정보 받아와서 레파지토리에 캐싱
        User loginUser = new User();
        loginUser.setUserId(prefLogin.getString("id", null));
        loginUser.setUserEmail(prefLogin.getString("email", null));
        loginUser.setUserName(prefLogin.getString("name", null));
        loginUser.setUserLoginMethod(prefLogin.getString("login_method", null));
        loginUser.setUserImageUrl(prefLogin.getString("profile_img_url", null));
        UserRepository.getInstance().setLoginUser(loginUser);

        initView();
    }

    private void initView() {

        adapter = new MainPagerAdapter(getSupportFragmentManager());

        // 뷰페이저 생성
        binding.viewPagerMain.setAdapter(adapter);
        binding.viewPagerMain.setPagingEnabled(false);
        binding.viewPagerMain.setOffscreenPageLimit(3);
        binding.viewPagerMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutMain));
        binding.viewPagerMain.setCurrentItem(0);

        // 하단 메인 탭 생성
        // 페이징 애니메이션 없애기 위해 smoothScroll false 설정
        binding.tabLayoutMain.setupWithViewPager(binding.viewPagerMain);
        binding.tabLayoutMain.addOnTabSelectedListener(this);

        // 탭 버튼 커스텀
        for(int i = 0 ; i < tabImages.length ; i++){
            binding.tabLayoutMain.getTabAt(i).setCustomView(makeTabButton(tabImages[i]));
        }

        binding.tabLayoutMain.getTabAt(0).select();

        // drawer 메뉴 뷰 생성
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_menu, MenuFragment.newInstance())
                .commit();
    }

    private ImageView makeTabButton(int imageResourceId){

        ImageView tab = new ImageView(this);
        tab.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tab.setScaleType(ImageView.ScaleType.CENTER_CROP);
        tab.setAdjustViewBounds(true);
        if(imageResourceId == tabImages[0]){
            tab.setAlpha(ALPHA_SELECTED);
            tab.setPadding(0, PADDING_ULTRA_SMALL, 0, PADDING_ULTRA_SMALL);
        } else {
            tab.setAlpha(ALPHA_UNSELECTED);
            tab.setPadding(PADDING_ULTRA_SMALL, PADDING_SMALL, PADDING_ULTRA_SMALL, PADDING_ULTRA_SMALL);
        }
        tab.requestLayout();
        tab.setImageResource(imageResourceId);

        return tab;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int position = tab.getPosition();

        // 페이지 로딩 요청
        Fragment fragment = adapter.getItem(position);
        if(fragment instanceof OnPageSelectedListener){
            ((OnPageSelectedListener) fragment).onPageSelected();
        }
        binding.viewPagerMain.setCurrentItem(position, false);

        // 탭 버튼 UI 변경
        ImageView imageView = (ImageView) tab.getCustomView();
        imageView.setAlpha(ALPHA_SELECTED);
        imageView.setPadding(0, PADDING_ULTRA_SMALL, 0, PADDING_ULTRA_SMALL);
        imageView.requestLayout();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab){
        ImageView imageView = (ImageView) tab.getCustomView();
        imageView.setAlpha(ALPHA_UNSELECTED);
        imageView.setPadding(PADDING_ULTRA_SMALL, PADDING_SMALL, PADDING_ULTRA_SMALL, PADDING_ULTRA_SMALL);
        imageView.requestLayout();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab){

    }

    /**
     * 드로어 메뉴 open/close 토글 */
    @Override
    public void onDrawerButtonClicked() {
        if(binding.drawerLayout.isDrawerOpen(binding.drawerMenu)) {
            binding.drawerLayout.closeDrawer(binding.drawerMenu);
        } else {
            binding.drawerLayout.openDrawer(binding.drawerMenu);
        }
    }

    @Override
    public void onBackPressed() {

        if(binding.drawerLayout.isDrawerOpen(binding.drawerMenu)){
            // 드로어 메뉴 열려있는 경우 - close
            binding.drawerLayout.closeDrawer(binding.drawerMenu);
        } else {
            // 그 외의 경우 뒤로가기 버튼 한번 더 누르면 종료시키기
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPressTime;

            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
                super.onBackPressed();
                overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
            } else {
                backPressTime = tempTime;
                makeToast(getString(R.string.strBackpressed));
            }
        }
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {

        private final int NUM_OF_TAB = 4;
        private Fragment[] fragments = new Fragment[NUM_OF_TAB];

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments[0] = MainFragment.newInstance();
            fragments[1] = CommunityFragment.newInstance();
            fragments[3] = AccountFragment.newInstance();

            if(LocaleHelper.getLanguage(getApplicationContext()).equals("ko"))
                fragments[2] = ShoppingFragment.newInstance();
            else
                fragments[2] = ShoppingEngFragment.newInstance();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return NUM_OF_TAB;
        }
    }
}
