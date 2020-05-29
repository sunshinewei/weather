package com.app.weilong.weather.net;





import com.app.weilong.lib.base.net.ApiManager;
import com.app.weilong.weather.bean.WeatherInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 */
public interface ServiceApi extends ApiManager {


    /**
     * 天气
     * @return
     */
    @GET("v3/weather/weatherInfo")
    Observable<WeatherInfoBean> weatherInfo(@Query("key") String key, @Query("city") String city,
                                            @Query("extensions") String extensions, @Query("output") String output);

}
