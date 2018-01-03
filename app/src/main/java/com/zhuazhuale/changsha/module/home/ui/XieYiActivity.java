package com.zhuazhuale.changsha.module.home.ui;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhuazhuale.changsha.R;
import com.zhuazhuale.changsha.view.activity.base.AppBaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/3.
 */

public class XieYiActivity extends AppBaseActivity {
    @BindView(R.id.wv_xieyi)
    WebView wv_xieyi;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_xieyi);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        wv_xieyi.loadUrl("http://zhuazhuale.com/rules.html");

        WebSettings webSettings = wv_xieyi.getSettings();//获得WebView的设置
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv_xieyi.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initEvent() {

    }
}
