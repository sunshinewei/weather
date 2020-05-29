package com.app.weilong.weather.activity;

import com.app.weilong.lib.base.basemvp.BaseAbstactPresenter;
import com.app.weilong.lib.base.net.ResultCallBack;
import com.app.weilong.weather.bean.WeatherInfoBean;
import com.app.weilong.weather.req.ReqWeatherInfoHeads;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 */
public class MainPresenter extends BaseAbstactPresenter<MainMContract.MainView,MainModel> implements MainMContract.MainPresenter {

    @Override
    public void weatherInfo(String key, String city, String extensions) {
        getModel().weatherInfo(key, city, extensions, new ResultCallBack<WeatherInfoBean>() {
            @Override
            public void onSuccess(WeatherInfoBean infoBean) {
                getView().weatherInfoView(infoBean);
            }

            @Override
            public void onError(String msgError) {
                getView().fail(msgError);
            }
        });
    }
}
