package com.gcfwpt.androidLearn.bean;

import java.io.Serializable;

/**
 * Created by LH on 2017/11/6.
 */

public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int error_code;
    public String reason;

    public LzyResponse toLzyResponse() {
        LzyResponse lzyResponse = new LzyResponse();
        lzyResponse.error_code = error_code;
        lzyResponse.reason = reason;
        return lzyResponse;
    }
}