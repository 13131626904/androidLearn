package com.gcfwpt.androidLearn.learn.learn2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gcfwpt.androidLearn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImmersionSystemActivity extends Activity {

    @BindView(R.id.ripple1)
    MaterialRippleLayout ripple1;
    @BindView(R.id.ripple2)
    MaterialRippleLayout ripple2;
    @BindView(R.id.ripple3)
    MaterialRippleLayout ripple3;
    @BindView(R.id.ripple4)
    MaterialRippleLayout ripple4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_system);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ripple1, R.id.ripple2, R.id.ripple3, R.id.ripple4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ripple1:
                showOperate1();
                break;
            case R.id.ripple2:
                showOperate2();
                break;
            case R.id.ripple3:
                showOperate3();
                break;
            case R.id.ripple4:
                showOperate4();
                break;
        }
    }

    private void showOperate1() {

    }

    private void showOperate2() {

    }

    private void showOperate3() {

    }

    private void showOperate4() {

    }
}
