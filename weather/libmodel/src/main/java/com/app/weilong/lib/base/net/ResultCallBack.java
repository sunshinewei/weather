package com.app.weilong.lib.base.net;


public interface ResultCallBack<T> {

    void onSuccess(T t);

    void onError(String msgError);

}
