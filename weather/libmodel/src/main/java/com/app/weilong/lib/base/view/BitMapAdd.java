package com.app.weilong.lib.base.view;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

/**
 * create by weilong on 2020/4/13
 * email: 1436699184@qq.com
 */
public class BitMapAdd {







    public static Drawable setDrawable(String imageUrl) {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }
        return drawable;
    }



}
