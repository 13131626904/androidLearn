package com.gcfwpt.androidLearn.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LH on 2017/11/3.
 */

public class MainBean implements Parcelable {
    /**
     * 分类
     * author LH
     * create at 2017/11/23 16:29
     */
    public static class Type{
        public static final int TYPE_OTHER  = 1;
        public static final int TYPE_VIEW  = 2;
    }

    private String mStrData;
    private Class toActivity;
    private int mIntType;

    public MainBean(String mStrData, Class toActivity, int mIntType) {
        this.mStrData = mStrData;
        this.toActivity = toActivity;
        this.mIntType = mIntType;
    }

    public String getmStrData() {
        return mStrData;
    }

    public void setmStrData(String mStrData) {
        this.mStrData = mStrData;
    }

    public Class getToActivity() {
        return toActivity;
    }

    public void setToActivity(Class toActivity) {
        this.toActivity = toActivity;
    }

    public int getmIntType() {
        return mIntType;
    }

    public void setmIntType(int mIntType) {
        this.mIntType = mIntType;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "mStrData='" + mStrData + '\'' +
                ", toActivity=" + toActivity +
                ", mIntType=" + mIntType +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mStrData);
        dest.writeSerializable(this.toActivity);
        dest.writeInt(this.mIntType);
    }

    protected MainBean(Parcel in) {
        this.mStrData = in.readString();
        this.toActivity = (Class) in.readSerializable();
        this.mIntType = in.readInt();
    }

    public static final Parcelable.Creator<MainBean> CREATOR = new Parcelable.Creator<MainBean>() {
        @Override
        public MainBean createFromParcel(Parcel source) {
            return new MainBean(source);
        }

        @Override
        public MainBean[] newArray(int size) {
            return new MainBean[size];
        }
    };
}
