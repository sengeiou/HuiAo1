package com.myp.huiao.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.myp.huiao.R;
import com.myp.huiao.base.BaseWebActivity;

import butterknife.Bind;

/**
 * Created by wuliang on 2017/9/27.
 * <p>
 * 加载H5的页面
 */

public class WebViewActvity extends BaseWebActivity {


    @Bind(R.id.web_view)
    WebView webView;

    String url;

    @Override
    protected int getLayout() {
        return R.layout.act_web_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goBack();
        setTitle("文章详情");

        initWebView(webView);
        url = getIntent().getExtras().getString("url", "");
        webView.loadUrl(url);
    }
}
