package com.app.weilong.weather.listener;

import com.amap.api.location.AMapLocation;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 * 地理位置回调
 */
public interface LocationListener {
    void result(AMapLocation aMapLocation);
}
