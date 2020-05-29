package com.app.weilong.lib.base.basemvp;

public interface BasePresenter<T extends BaseView> {

    void attach(T view);


    void detach();

}
