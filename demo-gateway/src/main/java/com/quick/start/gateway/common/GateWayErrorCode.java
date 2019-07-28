package com.quick.start.gateway.common;

public enum GateWayErrorCode {
    GATEWAY_ERROR(111, "网关认证失败"),
    GATEWAY_CONNECT_TIME_OUT(112, "请求超时"),
    GATEWAY_NOT_FOUND_SERVICE(113, "服务未找到"),
    SYSTEM_ERROR(114, "系统错误");

    private long code;
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    GateWayErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
