package com.app.weilong.lib.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.weilong.lib.base.utils.SizeUtils;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * create by weilong on 2020/4/27
 * email: 1436699184@qq.com
 */
public class MyTabLayout extends TabLayout {
    public MyTabLayout(@NonNull Context context) {
        super(context);
    }

    public MyTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dp10 = SizeUtils.dip2px(getContext(), 12);
        LinearLayout mTabStrip = (LinearLayout) this.getChildAt(0);
        try {
            Field mTabs = TabLayout.class.getDeclaredField("mTabs");
            mTabs.setAccessible(true);
            ArrayList<Tab> tabs = (ArrayList<Tab>) mTabs.get(this);
            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                Tab tab = tabs.get(i);
                Field mView = tab.getClass().getDeclaredField("mView");
                mView.setAccessible(true);
                Object tabView = mView.get(tab);
                Field mTextView = getContext().getClassLoader().loadClass("android.support.design.widget.TabLayout$TabView").getDeclaredField("mTextView");
                mTextView.setAccessible(true);
                TextView textView = (TextView) mTextView.get(tabView);
                float textWidth = textView.getPaint().measureText(textView.getText().toString());
                View child = mTabStrip.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) textWidth, LinearLayout.LayoutParams.MATCH_PARENT);
                params.leftMargin = dp10;
                params.rightMargin = dp10;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
