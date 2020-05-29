package com.app.weilong.lib.base.net;

/**
 * create by weilong on 2020/5/22
 * email: 1436699184@qq.com
 */
public class ApiException extends RuntimeException {
    private String errorCode;

    private String msg;
    public ApiException(String code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ApiException setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ApiException setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }
}
