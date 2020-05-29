package com.app.weilong.lib.base.basemvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseMVPFragment extends BaseFragment implements BaseView{


    private ProxyFragment proxyFragment;

    private ProxyFragment createProxyFragment(){
        if (proxyFragment==null){
            return new ProxyFragment(this);
        }

        return proxyFragment;
    }


    public abstract int getLayoutId();

                                          

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        proxyFragment=createProxyFragment();
        proxyFragment.bindPresenter();
        initMvpData(savedInstanceState);
    }


    public abstract void initMvpData(@Nullable Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        proxyFragment.unBindPresenter();
    }
}
