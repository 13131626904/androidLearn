package com.gcfwpt.androidLearn.bean;

import java.io.Serializable;

/**
 * Created by LH on 2017/11/6.
 */

public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int error_code;
    public String reason;
    public T result;

    @Override
    public String toString() {
        return "LzyResponse{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}