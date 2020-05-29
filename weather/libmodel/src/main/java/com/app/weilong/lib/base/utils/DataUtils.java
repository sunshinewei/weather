package com.app.weilong.lib.base.utils;

import java.text.DecimalFormat;

/**
 * create by weilong on 2020/4/26
 * email: 1436699184@qq.com
 */
public class DataUtils {



    public static String float2String(float num){
        DecimalFormat df   = new DecimalFormat("######0.00");
        String format = df.format(num);
        return format;
    }
}
