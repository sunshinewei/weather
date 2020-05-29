package com.app.weilong.lib.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.app.weilong.lib.base.MyApplication;

/**
 * create by weilong on 2020/4/3
 * email: 1436699184@qq.com
 */
public class ClipboardUtils {

    /**
     * 获取剪贴板的文本，仅获取Item中类型是String的文本
     *
     * @return 剪贴板的文本
     */
    public static String getTextFromText() {
        ClipboardManager clipboard = (ClipboardManager) MyApplication.getAppConext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0 && EmptyUtils.isNotEmpty(clip.getItemAt(0).getText())) {
            return clip.getItemAt(0).getText().toString();
        }
        return null;
    }
}
