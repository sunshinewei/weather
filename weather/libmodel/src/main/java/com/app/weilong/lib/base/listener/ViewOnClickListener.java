package com.app.weilong.lib.base.listener;

import android.view.View;

public abstract class ViewOnClickListener implements View.OnClickListener {
    long timeInterval=1000l;

    long lastTime=0;
    @Override
    public void onClick(View v) {

        long current=System.currentTimeMillis();
        //时间戳内点击了
        if (current-lastTime<timeInterval){
            onUnsingleClick(v);
        }else {
            onSingleClick(v);
            lastTime=current;
        }
    }

    /**
     * 单点
     * @param v
     */
    public void onSingleClick(View v){

    }
    /**
     *不限制
     * @param v
     */
    public void onUnsingleClick(View v){

    }
}
