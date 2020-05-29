package com.app.weilong.lib.base.net;

public interface CallBackImp<T extends BaseResponse> {


    void onSuccess(T t);

    void onError(String msg);
}
