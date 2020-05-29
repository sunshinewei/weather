package com.app.weilong.lib.base.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * create by weilong on 2020/4/3
 * email: 1436699184@qq.com
 */
public class TimeDownTimer extends CountDownTimer {


    private String type;
    private TextView timeView;
    private TimeCallBack mCallBack;
    public TimeDownTimer(long totalTime, long countDown, TextView timeView,String type,TimeCallBack timeCallBack){
        super(totalTime,countDown);
        this.timeView=timeView;
        this.type=type;
        this.mCallBack=timeCallBack;
    }
    @Override
    public void onTick(long millisUntilFinished) {

        if (timeView!=null){

            if (type.equals("login")){
                timeView.setText("重新获取"+millisUntilFinished/1000+"s");
                timeView.setTextColor(StyleUtils.parseColor("#ff999999"));
            }else if (type.equals("ads")){
                timeView.setText("跳过"+millisUntilFinished/1000+"s");
            }else if (type.equals("alert")){
                timeView.setText("("+millisUntilFinished/1000+"s)");
            }
        }
    }

    @Override
    public void onFinish() {
        if (mCallBack!=null){
            mCallBack.onFinish();
        }
    }




    public interface TimeCallBack{
        void onFinish();
    }

}
