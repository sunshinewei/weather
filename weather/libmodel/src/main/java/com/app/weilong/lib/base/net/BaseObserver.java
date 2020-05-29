package com.app.weilong.lib.base.net;


import com.app.weilong.lib.base.utils.LogUtils;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public class BaseObserver<M extends BaseResponse> implements Observer<M> {


    private CallBackImp mCallBackImp;
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(M t) {

        LogUtils.debug("BaseObserver-----debug   :  "+t.toString());
        switch (t.getStatus()){
            case "0":
                if (mCallBackImp!=null){
                    mCallBackImp.onError(t.getInfo());
                }
                break;
            case "1":
                if (mCallBackImp!=null){
                    mCallBackImp.onSuccess(t);
                }
                break;
        }
    }
    public BaseObserver setmCallBackImp(CallBackImp mCallBackImp) {
        this.mCallBackImp = mCallBackImp;
        return this;
    }
    @Override
    public void onError(Throwable e) {
        LogUtils.error("BaseObserver  onError--------------------"+e.fillInStackTrace());
        if (e instanceof HttpException) {
            mCallBackImp.onError("服务暂不可用");
        } else if (e instanceof IOException) {
            mCallBackImp.onError("连接失败");
        } else if (e instanceof ApiException) {
            mCallBackImp.onError(e.getMessage());
        }else {
            mCallBackImp.onError("加载失败!");
        }
    }

    @Override
    public void onComplete() {
        LogUtils.debug("BaseObserver-------------------完成onComplete");
    }

}
