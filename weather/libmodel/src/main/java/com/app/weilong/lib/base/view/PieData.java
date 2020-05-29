package com.app.weilong.lib.base.view;

import androidx.annotation.NonNull;

/**
 * create by weilong on 2020/4/24
 * email: 1436699184@qq.com
 */
public class PieData {

    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    private int color = 0;      // 颜色
    private float angle = 0;    // 角度

    public PieData(@NonNull String name, @NonNull float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public PieData setName(String name) {
        this.name = name;
        return this;
    }

    public float getValue() {
        return value;
    }

    public PieData setValue(float value) {
        this.value = value;
        return this;
    }

    public float getPercentage() {
        return percentage;
    }

    public PieData setPercentage(float percentage) {
        this.percentage = percentage;
        return this;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }

    public PieData setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public PieData setColor(int color) {
        this.color = color;
        return this;
    }
}
