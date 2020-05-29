package com.app.weilong.lib.base.basemvp;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public abstract class BaseAbstactPresenter<V extends BaseView,M extends BaseModel> implements BasePresenter{
    protected SoftReference<BaseView> mSoftRefereView;
    private V mProxyView;

    private M dataModel;

    @Override
    public void attach(BaseView view) {
        mSoftRefereView=new SoftReference<>(view);

        /**
         * 动态代理，获取View
         */
        mProxyView= (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (mSoftRefereView==null || getView()==null){
                    return null;
                }

//                LogUtils.error("============"+method+"--"+mSoftRefereView.get());
                return method.invoke(mSoftRefereView.get(),args);
            }
        });


        /**
         * 拿到Model
         */
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();

        if (genericSuperclass!=null){
            Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();

            try {
                dataModel= (M) ((Class<?>)actualTypeArguments[1]).newInstance();

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


    public M getModel(){
        return dataModel;
    }

    public V getView(){
        return mProxyView;
    }

    @Override
    public void detach() {
        mSoftRefereView.clear();
        if (mSoftRefereView!=null){
            mSoftRefereView=null;
        }
    }
}
