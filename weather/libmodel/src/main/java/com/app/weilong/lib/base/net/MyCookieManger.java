package com.app.weilong.lib.base.net;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * create by weilong on 2020/4/22
 * email: 1436699184@qq.com
 */
public class MyCookieManger implements CookieJar {


    static MyCookieManger cookieManger;

    private MyCookieManger(){

    }


    public static MyCookieManger initCookies(){
        if (cookieManger==null){
            return new MyCookieManger();
        }
        return cookieManger;
    }

    static List<Cookie> cookiesList;
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        Log.e("保存的cookies:" , cookies+" url:"+url);

        cookiesList=cookies;
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {

        Log.e("请求的cookies:" , cookiesList+" url:"+url);
        return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
    }
}
