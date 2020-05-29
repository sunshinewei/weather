package com.app.weilong.lib.base;

import android.app.Application;
import android.content.Context;

/**
 * create by weilong on 2020/5/21
 * email: 1436699184@qq.com
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        mContextApp = getApplicationContext();
    }

    static Context mContextApp;

    public static Context getAppConext() {

        return mContextApp;
    }
}
