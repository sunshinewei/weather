package com.app.weilong.lib.base.net;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {


    private String status;

    private String info;

    private String infocode;


    public String getStatus() {
        return status;
    }

    public BaseResponse<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public BaseResponse<T> setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getInfocode() {
        return infocode;
    }

    public BaseResponse<T> setInfocode(String infocode) {
        this.infocode = infocode;
        return this;
    }
}
