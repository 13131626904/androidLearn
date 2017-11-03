package com.gcfwpt.androidLearn.bean;

/**
 * Created by LH on 2017/11/3.
 */

public class MainBean {
    private String mStrData;
    private Class toActivity;

    public MainBean(String mStrData, Class toActivity) {
        this.mStrData = mStrData;
        this.toActivity = toActivity;
    }

    public String getmStrData() {
        return mStrData;
    }

    public Class getToActivity() {
        return toActivity;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "mStrData='" + mStrData + '\'' +
                '}';
    }
}
