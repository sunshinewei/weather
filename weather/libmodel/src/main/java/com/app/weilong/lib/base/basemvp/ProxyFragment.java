package com.app.weilong.lib.base.basemvp;

public class ProxyFragment<V extends BaseView> extends IProxyImp {
    public ProxyFragment(BaseView mView) {
        super(mView);
    }
}
