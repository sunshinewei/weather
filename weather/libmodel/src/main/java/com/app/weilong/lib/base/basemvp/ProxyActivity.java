package com.app.weilong.lib.base.basemvp;

public class ProxyActivity<V extends  BaseView> extends IProxyImp {

    public ProxyActivity(BaseView mView) {
        super(mView);
    }
}
