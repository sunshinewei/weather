package com.app.weilong.lib.base.view.pick;

import android.view.View;

/**
 * create by weilong on 2020/4/26
 * email: 1436699184@qq.com
 */
public interface IPickerViewOperation {

    int getSelectedItemOffset();

    int getVisibleItemNumber();

    int getLineColor();

    void updateView(View itemView, boolean isSelected);

}
