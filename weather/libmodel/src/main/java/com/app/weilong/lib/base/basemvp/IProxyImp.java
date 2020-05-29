package com.app.weilong.lib.base.basemvp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IProxyImp implements IProxy {

    private BaseView mView;
    private List<BasePresenter> mInjectPresenters;

    public IProxyImp(BaseView mView){
        this.mView=mView;
        mInjectPresenters=new ArrayList<>();
    }


    @Override
    public void bindPresenter() {
        Field[] declaredFields = mView.getClass().getDeclaredFields();
        for (Field field:declaredFields){
            //获取注解类型
            InjectPresenter injectPresenter=field.getAnnotation(InjectPresenter.class);
            if (injectPresenter!=null){
                try {
                    Class<? extends BasePresenter> type = (Class<? extends BasePresenter>) field.getType();
                    BasePresenter basePresenter = type.newInstance();
                    basePresenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView,basePresenter);
                    mInjectPresenters.add(basePresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw new RuntimeException("SubClass must extends Class:BasePresenter");
                }

            }
        }
    }

    @Override
    public void unBindPresenter() {
        for (BasePresenter presenter : mInjectPresenters) {
            presenter.detach();
        }
        mInjectPresenters.clear();
        mInjectPresenters = null;
    }
}
