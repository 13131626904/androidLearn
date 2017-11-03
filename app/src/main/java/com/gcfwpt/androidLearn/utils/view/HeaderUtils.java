package com.gcfwpt.androidLearn.utils.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.callback.BaseHeaderCallBack;
import com.gcfwpt.androidLearn.callback.BaseLeftCallBack;
import com.gcfwpt.androidLearn.callback.BaseRightCallBack;
import com.gcfwpt.androidLearn.utils.common.StringUtils;

/**
 * Created by LH on 2017/11/2.
 */

public class HeaderUtils implements View.OnClickListener {
    private Context context;
    private ViewGroup headerView;
    private BaseHeaderCallBack headerCallBack;
    private BaseLeftCallBack leftCallBack;
    private BaseRightCallBack rightCallBack;
    private RelativeLayout relTitleBar;// 顶部导航栏
    private TextView moduleTextView;
    private Button topLeftButton;
    private TextView topRightText;
    private ImageView topRightImg;
    public HeaderUtils(Context context){
        this.context=context;
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

    /**
     * 设置头部
     * author LH
     * create at 2017/11/2 13:46
     */
    public void setHeaderView(ViewGroup headerView) {
        this.headerView = headerView;
    }


    /**
     * 设置头部背景颜色
     * author LH
     * create at 2017/11/2 13:46
     */
    public void setTitleBarBackgroundColor(int color) {
        if (relTitleBar == null) {
            relTitleBar = (RelativeLayout) headerView.findViewById(R.id.relTitleBar);
        }
        relTitleBar.setBackgroundColor(context.getResources().getColor(color));
    }
    
    /**
     * 设置头部文字颜色
     * author LH
     * create at 2017/11/2 13:47
     */
    public void setModuleTitleColor(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) headerView.findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setTextColor(context.getResources().getColor(resourceId));
    }
    
    /**
     * 设置头部文字
     * author LH
     * create at 2017/11/2 13:47
     */
    public void setModuleTitle(int resourceId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) headerView.findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setText(resourceId);
    }
    /**
     * 设置头部文字
     * author LH
     * create at 2017/11/2 13:48
     */
    public void setModuleTitle(String text) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) headerView.findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setVisibility(View.VISIBLE);
        moduleTextView.setText(text);
    }
    /**
     * 设置头部图片
     * author LH
     * create at 2017/11/2 13:48
     */
    public void setModuleTitleImg(int resId) {
        if (moduleTextView == null) {
            moduleTextView = (TextView) headerView.findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setVisibility(View.VISIBLE);
        moduleTextView.setText("");
        moduleTextView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }
    /**
     * 隐藏头部文字
     * author LH
     * create at 2017/11/2 13:48
     */
    public void hideModuleTitle() {
        if (moduleTextView == null) {
            moduleTextView = (TextView) headerView.findViewById(R.id.module_title_text_view);
        }
        moduleTextView.setVisibility(View.GONE);
    }
    /**
     * 隐藏左侧按钮
     * author LH
     * create at 2017/11/2 13:51
     */
    public void hideTopLeftButton() {
        if (topLeftButton == null) {
            topLeftButton = (Button) headerView.findViewById(R.id.top_left_button);
        }
        topLeftButton.setVisibility(View.GONE);
    }
    /**
     * 显示左侧按钮，只有箭头
     * author LH
     * create at 2017/11/2 13:53
     */
    public void showTopLeftButton() {
        showTopLeftButton("", R.drawable.btn_back);
    }
    /**
     * 显示左侧按钮，文字和箭头
     * author LH
     * create at 2017/11/2 13:53
     */
    public void showTopLeftButton(String text) {
        showTopLeftButton(text, R.drawable.btn_back);
    }

    /**
     * 显示左侧按钮，只有文字
     * author LH
     * create at 2017/11/2 13:54
     */
    public void showTopLeftText(String text) {
        showTopLeftButton(text, 0);
    }

    private void showTopLeftButton(String text, int resId) {

        if (topLeftButton == null) {
            topLeftButton = (Button) headerView.findViewById(R.id.top_left_button);
            topLeftButton.setOnClickListener(this);
        }
        topLeftButton.setVisibility(View.VISIBLE);
        topLeftButton.setText(StringUtils.isEmpty(text) ? "" : text);
        topLeftButton.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    /**
     * 显示右侧文字
     * author LH
     * create at 2017/11/2 13:56
     */
    public void showTopRightText(String string) {
        if (topRightText == null) {
            topRightText = (TextView) headerView.findViewById(R.id.top_right_text);
            topRightText.setOnClickListener(this);
        }
        topRightText.setVisibility(View.VISIBLE);
        topRightText.setText(string);
    }

    /**
     * 隐藏右侧文字
     * author LH
     * create at 2017/11/2 13:56
     */
    public void hideTopRightText() {
        if (topRightText == null) {
            topRightText = (TextView) headerView.findViewById(R.id.top_right_text);
        }
        topRightText.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示右侧图片
     * author LH
     * create at 2017/11/2 13:57
     */
    public void showTopRightImg(int img) {
        if (topRightImg == null) {
            topRightImg = (ImageView) headerView.findViewById(R.id.top_right_img);
            topRightImg.setOnClickListener(this);
        }
        topRightImg.setVisibility(View.VISIBLE);
        topRightImg.setImageResource(img);
    }

    /**
     * 隐藏右侧图片
     * author LH
     * create at 2017/11/2 13:57
     */
    public void hideTopRightImg() {
        if (topRightImg == null) {
            topRightImg = (ImageView) headerView.findViewById(R.id.top_right_img);
        }
        topRightImg.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.top_left_button:
                if(leftCallBack!=null){
                    leftCallBack.onHeaderLeftClick(view);
                }
                break;
            case R.id.top_right_text:
                if(headerCallBack!=null){
                    headerCallBack.onHeaderTitleClick(view);
                }
                break;
            case R.id.top_right_img:
                if(rightCallBack!=null){
                    rightCallBack.onHeaderRightClick(view);
                }
                break;
        }
    }
}
