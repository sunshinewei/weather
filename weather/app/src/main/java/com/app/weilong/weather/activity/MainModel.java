package com.app.weilong.weather.activity;

import com.app.weilong.lib.base.basemvp.BaseModel;
import com.app.weilong.lib.base.net.ApiService;
import com.app.weilong.lib.base.net.BaseObserver;
import com.app.weilong.lib.base.net.BaseResponse;
import com.app.weilong.lib.base.net.CallBackImp;
import com.app.weilong.lib.base.net.ImpRetrofitClient;
import com.app.weilong.lib.base.net.ResultCallBack;
import com.app.weilong.weather.bean.WeatherInfoBean;
import com.app.weilong.weather.net.ImRetrofitClient;
import com.app.weilong.weather.net.ServiceApi;
import com.app.weilong.weather.req.ReqWeatherInfoHeads;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 */
public class MainModel extends BaseModel implements MainMContract.MainModel {

    @Override
    public void weatherInfo(String key, String city, String extensions, ResultCallBack callBack) {
       ImRetrofitClient
               .newInstance()
               .mApiManager
               .weatherInfo(key,city,extensions,"JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<WeatherInfoBean>()
                .setmCallBackImp(new CallBackImp<WeatherInfoBean>() {
                    @Override
                    public void onSuccess(WeatherInfoBean infoBean) {
                        callBack.onSuccess(infoBean);
                    }

                    @Override
                    public void onError(String msg) {
                       callBack.onError(msg);
                    }
                }));


    }
}
