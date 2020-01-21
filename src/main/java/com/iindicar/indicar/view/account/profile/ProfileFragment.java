package com.iindicar.indicar.view.account.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.FragmentProfileBinding;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.listener.OnPageSelectedListener;
import com.iindicar.indicar.view.main.LanguageActivity;
import com.iindicar.indicar.view.shopping.shoppingDetail.ShoppingDetailActivity;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding>{

    private static final int REQUEST_LANGUAGE = 1004; // 언어 설정
    public static final int REQUEST_ACCOUNT = 1005; // 계정 설정

    private User user;

    public ProfileFragment(){}

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }
    
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        user = UserRepository.getInstance().getLoginUser();
        binding.setUser(user);

        // 알림 설정 버튼 리스너
        binding.btnNotification.setOnClickListener(v ->
                startActivity(new Intent(context, NotifySettingActivity.class)));

        // 언어 설정 버튼 리스너
        binding.btnLanguage.setOnClickListener(v ->
                startActivityForResult(new Intent(context, LanguageActivity.class), REQUEST_LANGUAGE));
        // 계정 설정 버튼 리스너
        binding.btnAccount.setOnClickListener(v ->
                startActivityForResult(new Intent(context, AccountSettingActivity.class), REQUEST_ACCOUNT));

        // 쇼핑몰 버튼 리스너
        binding.btnShopping.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShoppingDetailActivity.class);
            intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, "https://smartstore.naver.com/indiparts");
            intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, getString(R.string.indiparts));
            intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
            startActivity(intent);
        });

        // 페이스북 버튼 리스너
        binding.btnFacebook.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShoppingDetailActivity.class);
            intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, "https://www.facebook.com/%EC%9D%B8%EB%94%94%ED%8C%8C%EC%B8%A0-2180126928666818/");
            intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, getString(R.string.app_name));
            intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
            startActivity(intent);
        });

        // 유튜브 버튼 리스너
        binding.btnYoutube.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShoppingDetailActivity.class);
            intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, "https://www.youtube.com/channel/UC4AgXrlp-YFGScOb718beig");
            intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, getString(R.string.app_name));
            intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
            startActivity(intent);
        });

        // 추천 버튼 리스너
        binding.btnShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.iindicar.indicar");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getString(R.string.share)));
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){
            return;
        }

        switch (requestCode){
            case REQUEST_LANGUAGE:
                //boolean isLanguageChanged = data.getBooleanExtra(LanguageSettingActivity.RESULT_LANGUAGE_CHANGED, false);
                //if (isLanguageChanged){
                    //getActivity().recreate();
                //}
                break;
            case REQUEST_ACCOUNT:
                boolean isUserNameChanged = data.getBooleanExtra(AccountSettingActivity.RESULT_USER_NAME_CHANGED, false);
                if(isUserNameChanged){
                    user = UserRepository.getInstance().getLoginUser();
                }
        }
    }

}
