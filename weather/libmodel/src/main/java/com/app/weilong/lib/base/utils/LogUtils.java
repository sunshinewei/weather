package com.app.weilong.lib.base.utils;

import android.util.Log;

public class LogUtils {


    static boolean isDebug=true;

    public static void error(String msg){
        if (isDebug){
            Log.e("error-type-----:",msg);
        }
    }

    public static void debug(String msg){
        if (isDebug){
            Log.d("debug-type-----:",msg);
        }
    }
    public static void warn(String msg) {
        if (isDebug){
            Log.w("warn-type-----:", msg);
        }
    }

}
