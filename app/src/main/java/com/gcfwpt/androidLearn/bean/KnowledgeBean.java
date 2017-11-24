package com.gcfwpt.androidLearn.bean;

/**
 * Created by LH on 2017/11/3.
 */

public class KnowledgeBean {
    private String mStrData;
    private Class toActivity;

    public KnowledgeBean(String mStrData, Class toActivity) {
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
