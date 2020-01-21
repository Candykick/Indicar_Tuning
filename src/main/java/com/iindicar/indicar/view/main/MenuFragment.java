package com.iindicar.indicar.view.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.user.UserRepository;
import com.iindicar.indicar.databinding.FragmentMenuBinding;
import com.iindicar.indicar.databinding.HolderMenuItemBinding;
import com.iindicar.indicar.model.User;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.view.BaseFragment;
import com.iindicar.indicar.view.adapter.BaseRecyclerAdapter;
import com.iindicar.indicar.view.listener.OnItemClickListener;
import com.iindicar.indicar.view.main.tutorial.TutorialActivity;
import com.iindicar.indicar.view.shopping.shoppingDetail.ShoppingDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends BaseFragment<FragmentMenuBinding> implements OnItemClickListener {

    public final ObservableInt currentPage = new ObservableInt(0);

    public final int INDEX_HOWTOUSE = 0;
    public final int INDEX_PARTNER = 1;
    public final int INDEX_FNQ = 2;
    public final int INDEX_TERMS = 3;
    public final int INDEX_VERSION = 4;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    public static MenuFragment newInstance(){
        return new MenuFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 하단뷰에 이벤트 전달 안함
        binding.container.setOnTouchListener((v, event) -> true);

        initView();
    }

    private void initView() {

        // 사용자 프로필 설정
        User user = UserRepository.getInstance().getLoginUser();
        binding.setUser(user);

        // 메뉴 리사이클러뷰 생성
        MenuAdapter adapter = new MenuAdapter(context);
        adapter.setOnItemClickListener(this);
        binding.recyclerDrawerMenu.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.recyclerDrawerMenu.setAdapter(adapter);
        binding.recyclerDrawerMenu.setNestedScrollingEnabled(false);
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = null;

        switch (position){
            case INDEX_HOWTOUSE: // 이용방법
                intent = new Intent(context, TutorialActivity.class);
                break;
            case INDEX_PARTNER: // 제휴요청
                if(LocaleHelper.getLanguage(getActivity().getApplicationContext()).equals("ko")) {
                    intent = new Intent(context, ShoppingDetailActivity.class);
                    intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, getString(R.string.strPartnership));
                    intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, resources.getString(R.string.partnership_title));
                    intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
                } else {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    String[] emailAdd = {getString(R.string.strIndicarEmail)};
                    intent.putExtra(Intent.EXTRA_EMAIL, emailAdd);
                }
                break;
            case INDEX_FNQ: // 1:1문의
                if(LocaleHelper.getLanguage(getActivity().getApplicationContext()).equals("ko")) {
                    intent = new Intent(context, ShoppingDetailActivity.class);
                    intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, getString(R.string.strContact));
                    intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, resources.getString(R.string.suggest_title));
                    intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
                } else {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    String[] emailAdd = {getString(R.string.strIndicarEmail)};
                    intent.putExtra(Intent.EXTRA_EMAIL, emailAdd);
                }
                break;
            case INDEX_TERMS: // 이용약관
                intent = new Intent(context, ShoppingDetailActivity.class);
                intent.putExtra(ShoppingDetailActivity.EXTRA_WEB_URL, resources.getString(R.string.strTermsUrl));
                intent.putExtra(ShoppingDetailActivity.EXTRA_PRODUCT_NAME, resources.getString(R.string.terms_title));
                intent.putExtra(ShoppingDetailActivity.EXTRA_BOARD_ID, "");
                break;
            case INDEX_VERSION: // 버전정보
                makeToast("made by Indicar Tuning");
                return;
        }

        startActivity(intent);
    }

    public class MenuItem {

        private String title;

        private String subtitle;

        public MenuItem(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }
    }

    public class MenuAdapter extends BaseRecyclerAdapter<MenuItem, MenuAdapter.ViewHolder> {

        private final int menuItemTitles[] = {
                R.string.how_to_use_title,
                R.string.partnership_title,
                R.string.suggest_title,
                R.string.terms_title,
                R.string.version_title
        };

        private final int menuItemSubtitles[] = {
                R.string.how_to_use_subtitle,
                R.string.partnership_subtitle,
                R.string.suggest_subtitle,
                R.string.terms_subtitle,
                R.string.version_subtitle
        };

        public MenuAdapter(Context context) {
            super(context);

            // 메뉴 리스트 초기화
            List<MenuItem> menuItemList = new ArrayList<>();
            for(int i = 0 ; i < menuItemTitles.length ; i++){
                MenuItem menuItem = new MenuItem(
                        resources.getString(menuItemTitles[i]),
                        resources.getString(menuItemSubtitles[i])
                );
                menuItemList.add(menuItem);
            }
            this.addItems(menuItemList);
        }

        @NonNull
        @Override
        public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_menu_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        protected void onBindView(ViewHolder holder, int position) {
            holder.binding.setItem(itemList.get(position));
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            protected HolderMenuItemBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
