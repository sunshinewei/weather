package com.app.weilong.lib.base.utils;


import com.app.weilong.lib.base.MyApplication;

/**
 * create by weilong on 2020/4/2
 * email: 1436699184@qq.com
 *
 * 存储用户信息
 *
 * 缓存
 *
 */
public class CacheUtils {

    private static ACache mCache;
    public static ACache newInstance(){
        if (mCache==null){
            mCache=ACache.get(MyApplication.getAppConext());
        }
        return mCache;
    }

}
