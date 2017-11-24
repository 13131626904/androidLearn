package com.gcfwpt.androidLearn.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by LH on 2017/11/7.
 */

public class ResultBean<T> implements Serializable{
    private ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "data=" + data +
                '}';
    }
}
