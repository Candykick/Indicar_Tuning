package com.iindicar.indicar.view.shopping.shoppingDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.source.shopping.ShoppingRepository;
import com.iindicar.indicar.utils.GlideUtil;
import com.iindicar.indicar.view.BaseActivity;
import com.iindicar.indicar.databinding.ActivityShoppingDetailBinding;

public class ShoppingDetailActivity extends BaseActivity<ActivityShoppingDetailBinding> implements ShoppingDetailContract.View{

    public static final String EXTRA_BOARD_ID = "EXTRA_BOARD_ID"; // 아직은 안쓰는데 나중에 서버에 조회해서 조회수 카운팅하기
    public static final String EXTRA_PRODUCT_NAME = "EXTRA_PRODUCT_NAME"; // 이건 임시
    public static final String EXTRA_WEB_URL = "EXTRA_WEB_URL";

    private ShoppingDetailPresenter presenter;

    private String boardId;
    private String productName;
    private String webUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_detail;
    }

    private void getExtraItems() {
        Intent intent = getIntent();
        boardId = "";
        productName = intent.getStringExtra(EXTRA_PRODUCT_NAME);
        webUrl = intent.getStringExtra(EXTRA_WEB_URL);
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtraItems();

        presenter = new ShoppingDetailPresenter(this, ShoppingRepository.getInstance(), boardId);

        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {

        // 툴바 생성
        binding.toolbar.btnLeft.setOnClickListener(v -> finish());
        binding.toolbar.tvCenter.setText(productName);

        // 웹뷰 생성
        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);

        // 웹뷰 성능향상 옵션
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setEnableSmoothTransition(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 인터넷 사용 안하는 웹뷰의 경우만 캐시를 안쓰는 것이 유리함
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        binding.webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        binding.webView.setScrollbarFadingEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            binding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        binding.webView.loadUrl(webUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 웹뷰에서 뒤로 갈 페이지가 있으면 웹뷰 뒤로가기
        if((keyCode == KeyEvent.KEYCODE_BACK) && binding.webView.canGoBack()){
            binding.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public Context getContext() {
        return this;
    }

}
