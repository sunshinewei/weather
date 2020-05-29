package com.app.weilong.lib.base.net;

import com.app.weilong.lib.base.MyApplication;
import com.app.weilong.lib.base.net.interceptor.LogInterceptor;
import com.app.weilong.lib.base.utils.NetUtils;
import com.app.weilong.lib.base.view.ToastUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpRetrofitClient{

    protected Retrofit retrofitBuild;

    protected ImpRetrofitClient(String url){

        long timeSeconds=3000;
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(timeSeconds, TimeUnit.SECONDS);
        builder.writeTimeout(timeSeconds,TimeUnit.SECONDS);
        builder.readTimeout(timeSeconds,TimeUnit.SECONDS);

        builder.cookieJar(MyCookieManger.initCookies());
        builder.addInterceptor(new LogInterceptor());


        retrofitBuild = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(url)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}


