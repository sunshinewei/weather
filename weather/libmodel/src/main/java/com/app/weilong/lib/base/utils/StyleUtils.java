package com.app.weilong.lib.base.utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;


/**
 * create by weilong on 2020/4/10
 * email: 1436699184@qq.com
 */
public class StyleUtils {


    /**
     * 字符串转换为int
     * @param color
     * @return
     */
    public static int parseColor(String color){
        return Color.parseColor(color);
    }


    /**
     * 设置背景主题颜色
     * @param view
     * @param color
     */
    public static void setStyleColor(View view, String color){
        GradientDrawable drawable= (GradientDrawable) view.getBackground();
        drawable.setColor(Color.parseColor(color));
    }








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
