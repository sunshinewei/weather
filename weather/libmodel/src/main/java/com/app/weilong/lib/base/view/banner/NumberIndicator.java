package com.app.weilong.lib.base.view.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.app.weilong.lib.R;
import com.app.weilong.lib.base.utils.SizeUtils;

/**
 * create by weilong on 2020/4/17
 * email: 1436699184@qq.com
 */
@SuppressLint("AppCompatCustomView")
public class NumberIndicator extends TextView {

    public NumberIndicator(Context context) {
        super(context);
        setTextColor(Color.WHITE);
        setTextSize(14);
        setBackgroundResource(R.drawable.btn_login_style);
        int padding = SizeUtils.dp2Pix(context, 5);
        setPadding(padding, padding, padding, padding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //保证TextIndicator的宽高一致(正方形)
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
