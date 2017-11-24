package com.gcfwpt.androidLearn;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.gcfwpt.androidLearn.base.BaseActivity;
import com.gcfwpt.androidLearn.callback.BaseLeftCallBack;
import com.gcfwpt.androidLearn.utils.view.HeaderUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    public final static String URL = "url";
    public final static String TITLE = "title";

    @BindView(R.id.pb_data)
    ProgressBar pbData;
    @BindView(R.id.wv_data)
    WebView wvData;

    private String mStrUrl;
    private String mStrTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mStrUrl = getIntent().getStringExtra(URL);
        mStrTitle = getIntent().getStringExtra(TITLE);
    }

    private void initView() {
        pbData.setMax(100);
        initHeader();
        initWebView();
    }

    private void initHeader() {
        showHeadView();
        HeaderUtils headerUtils = getHeaderUtils();
        headerUtils.setModuleTitle(mStrTitle);
        headerUtils.showTopLeftButton();
        headerUtils.setLeftCallBack(new BaseLeftCallBack() {
            @Override
            public void onHeaderLeftClick(View view) {
                finish();
            }
        });
    }

    private void initWebView() {
        wvData.getSettings().setJavaScriptEnabled(true);
        wvData.getSettings().setSupportZoom(true);
        wvData.getSettings().setBuiltInZoomControls(true);
        wvData.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbData.setProgress(newProgress);
                if (newProgress >= 100) {
                    pbData.setVisibility(View.GONE);
                }
            }
        });
        wvData.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvData.loadUrl(mStrUrl);
    }
}
