package com.gcfwpt.androidLearn.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.callback.BaseHeaderCallBack;
import com.gcfwpt.androidLearn.callback.BaseLeftCallBack;
import com.gcfwpt.androidLearn.callback.BaseReplayCallBack;
import com.gcfwpt.androidLearn.callback.BaseRightCallBack;
import com.gcfwpt.androidLearn.utils.view.HeaderUtils;
import com.gcfwpt.androidLearn.view.custom.BaseProgressBarLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by LH on 2017/10/11.
 */

public class BaseActivity extends AutoLayoutActivity implements View.OnClickListener {
    public BaseAnimatorSet mBasIn;
    public BaseAnimatorSet mBasOut;
    private ImmersionBar mImmersionBar;

    private FrameLayout mFraLayoutContent;
    private FrameLayout mFraLayoutHeadView;
    private RelativeLayout mRelLayoutBase;
    private BaseProgressBarLayout mLoadingBar;
    private RelativeLayout errorLayout;
    private RelativeLayout emptyLayout;
    private LinearLayout mResetButton;
    private TextView tvEmtyHit;
    private BaseReplayCallBack replayCallBack;
    private BaseHeaderCallBack headerCallBack;
    private BaseLeftCallBack leftCallBack;
    private BaseRightCallBack rightCallBack;
    private HeaderUtils headerUtils;

    private int headViewResId = R.layout.layout_base_header;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseControl();
        initBaseData();
        initBaseBarColor();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mRelLayoutBase = (RelativeLayout) findViewById(R.id.relLayoutBase);
        mFraLayoutContent = (FrameLayout) findViewById(R.id.fraLayoutContent);
        mFraLayoutHeadView = (FrameLayout) findViewById(R.id.fraLayoutHeadView);


        LayoutInflater.from(this).inflate(layoutResID, mFraLayoutContent, true);
        LayoutInflater.from(this).inflate(headViewResId, mFraLayoutHeadView, true);
        mLoadingBar = (BaseProgressBarLayout) findViewById(R.id.load_bar_layout);
        errorLayout = (RelativeLayout) findViewById(R.id.errorLayout);
        emptyLayout = (RelativeLayout) findViewById(R.id.emptyLayout);
        mResetButton = (LinearLayout) findViewById(R.id.click_replay);
        mResetButton.setOnClickListener(this);
        headerUtils.setHeaderView(mFraLayoutHeadView);
    }

    private void initBaseData() {
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        headerUtils = new HeaderUtils(this);
        headerUtils.setLeftCallBack(leftCallBack);
        headerUtils.setHeaderCallBack(headerCallBack);
        headerUtils.setRightCallBack(rightCallBack);
    }

    /**
     * 初始化界面控制
     * author LH
     * create at 2017/11/1 16:31
     */
    private void initBaseControl() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    /**
     * 设置沉浸式状态栏
     * author LH
     * create at 2017/11/1 16:19
     */
    private void initBaseBarColor() {
        mImmersionBar = ImmersionBar.with(this)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f);
        mImmersionBar.init();
    }

    /**
     * 获取头部工具类
     * author LH
     * create at 2017/11/2 14:21
     */
    public HeaderUtils getHeaderUtils() {
        return headerUtils;
    }

    /**
     * 设置自定义头部(放到setContentView方法之前)
     * author LH
     * create at 2017/11/2 11:39
     */
    protected void setHeadView(int layoutResID) {
        this.headViewResId = layoutResID;
    }

    /**
     * 隐藏头部
     * author LH
     * create at 2017/11/2 11:40
     */
    protected void hideHeadView() {
        mFraLayoutHeadView.setVisibility(View.GONE);
    }

    /**
     * 显示头部
     * author LH
     * create at 2017/11/2 11:40
     */
    protected void showHeadView() {
        mFraLayoutHeadView.setVisibility(View.VISIBLE);
    }


    /**
     * 显示加载中布局
     * author LH
     * create at 2017/11/2 11:42
     * @param transparent 是否背景透明
     */
    public void showLoadingBar(boolean transparent) {
        mLoadingBar.setBackgroundColor(transparent ? Color.TRANSPARENT : getResources().getColor(R.color.main_bg));
        mLoadingBar.show();
    }

    /**
     * 隐藏加载布局
     * author LH
     * create at 2017/11/2 11:42
     */
    public void hideLoadingBar() {
        mLoadingBar.hide();
    }

    /**
     * 加载布局是否显示
     * author LH
     * create at 2017/11/2 11:43
     */
    public boolean isLoadingBarShow() {
        return mLoadingBar.getVisibility() == View.VISIBLE;
    }

    /**
     * 显示出错布局
     * author LH
     * create at 2017/11/2 11:48
     */
    protected void showErrorLayout() {
        errorLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏出错布局
     * author LH
     * create at 2017/11/2 11:47
     */
    protected void hideErrorLayout() {
        errorLayout.setVisibility(View.GONE);
    }

    /**
     * 显示空页面
     * author LH
     * create at 2017/11/2 11:49
     */
    public void showEmptyLayout() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 显示空页面
     * author LH
     * create at 2017/11/2 11:49
     */
    public void showEmptyLayout(String emptyHit) {
        emptyLayout.setVisibility(View.VISIBLE);
        if (tvEmtyHit == null) {
            tvEmtyHit = (TextView) findViewById(R.id.tv_empty);
        }
        tvEmtyHit.setText(emptyHit);
    }

    /**
     * 隐藏空页面
     * author LH
     * create at 2017/11/2 13:35
     */
    public void hideEmptyLayout() {
        emptyLayout.setVisibility(View.GONE);
    }


    public void setReplayCallBack(BaseReplayCallBack replayCallBack) {
        this.replayCallBack = replayCallBack;
    }

    public void setHeaderCallBack(BaseHeaderCallBack headerCallBack) {
        this.headerCallBack = headerCallBack;
    }

    public void setLeftCallBack(BaseLeftCallBack leftCallBack) {
        this.leftCallBack = leftCallBack;
    }

    public void setRightCallBack(BaseRightCallBack rightCallBack) {
        this.rightCallBack = rightCallBack;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.click_replay:
                Logger.e("lh","****click_replay");
                if(replayCallBack!=null){
                    replayCallBack.onReplayNetClick(view);
                }
                break;
        }
    }
}
