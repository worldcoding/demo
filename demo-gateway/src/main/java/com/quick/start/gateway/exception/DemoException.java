package com.quick.start.gateway.exception;

public class DemoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private long errorCode;

    public DemoException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public DemoException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public DemoException(String msg, long errorCode) {
        super(msg);
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public DemoException(String msg, long errorCode, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 整合异常信息
     * @return
     */
    @Override
    public String toString(){

        return String.valueOf(this.errorCode) +
                ":" +
                this.msg;
    }
}
