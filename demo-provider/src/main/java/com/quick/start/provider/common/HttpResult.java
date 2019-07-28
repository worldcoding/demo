package com.quick.start.provider.common;

import com.alibaba.fastjson.JSONObject;

public class HttpResult {
    private long code;
    private String msg;
    private JSONObject data;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public HttpResult() {
    }

    public HttpResult(long code, String msg, JSONObject data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public HttpResult(long code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
