package com.gcfwpt.androidLearn.learn.learn1;

import android.os.Bundle;

import com.gcfwpt.androidLearn.R;
import com.gcfwpt.androidLearn.base.BaseActivity;
import com.gcfwpt.androidLearn.utils.view.HeaderUtils;

public class LearnNetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_net);
        initView();
    }

    private void initView() {
        initHeader();
    }

    private void initHeader() {
        showHeadView();
        HeaderUtils headerUtils = getHeaderUtils();
        headerUtils.setModuleTitle("网络加载");
        headerUtils.showTopLeftButton();
    }
}
