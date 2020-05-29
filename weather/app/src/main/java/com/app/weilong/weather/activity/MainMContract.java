package com.app.weilong.weather.activity;

import com.app.weilong.lib.base.basemvp.BaseView;
import com.app.weilong.lib.base.net.ResultCallBack;
import com.app.weilong.weather.bean.WeatherInfoBean;
import com.app.weilong.weather.req.ReqWeatherInfoHeads;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 */
public interface MainMContract {

    interface MainModel{
        void weatherInfo(String key,String city,String extensions, ResultCallBack callBack);
    }

    interface MainView extends BaseView {
        
        void weatherInfoView(WeatherInfoBean bean);

        void fail(String msg);
    }

    interface MainPresenter{
        void weatherInfo(String key, String city, String extensions);
    }
}
