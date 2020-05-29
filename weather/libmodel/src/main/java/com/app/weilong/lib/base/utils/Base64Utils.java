package com.app.weilong.lib.base.utils;


import android.util.Base64;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * create by weilong on 2020/5/12
 * email: 1436699184@qq.com
 */
public class Base64Utils {

    static byte[] data = null;
    public static byte[] ImageToBase64(String imgPath) {

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        // 返回Base64编码过的字节数组字符串
       return Base64.encode(data,data.length);
    }
}
