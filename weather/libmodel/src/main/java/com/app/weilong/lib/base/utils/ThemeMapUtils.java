package com.app.weilong.lib.base.utils;

/**
 * create by weilong on 2020/4/3
 * email: 1436699184@qq.com
 */
public class ThemeMapUtils {
    public static int getTypeDpi(){
        int type=1;//0.hdpi 1,xxhdpi 2.xxhdp
        switch (ScreenUtils.getScreenDensityDpi()){
            case 320:
                type=1;
                break;
            case 240:
                type=0;
                break;
            case 480:
                type=1;
                break;
            case 640:
                type=2;
                break;
            default:
                    type=1;
        }
        return type;
    }
}
