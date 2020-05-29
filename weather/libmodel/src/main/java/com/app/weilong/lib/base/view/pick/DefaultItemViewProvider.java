package com.app.weilong.lib.base.view.pick;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.weilong.lib.R;

/**
 * create by weilong on 2020/4/26
 * email: 1436699184@qq.com
 */
public class DefaultItemViewProvider implements IViewProvider<String> {
    @Override
    public int resLayout() {
        return R.layout.scroll_picker_default_item_layout;
    }

    @Override
    public void onBindView(@NonNull View view, @Nullable String text) {
        TextView tv = view.findViewById(R.id.tv_content);
        if (text!=null){
            tv.setText(text);
            view.setTag(text);
            tv.setTextSize(18);
        }else {
            tv.setText("");
            view.setTag("");
            tv.setTextSize(18);
        }

    }

    @Override
    public void updateView(@NonNull View itemView, boolean isSelected) {
        TextView tv = itemView.findViewById(R.id.tv_content);
        tv.setTextSize(isSelected ? 18 : 14);
        tv.setTextColor(Color.parseColor(isSelected ? "#ffff8722" : "#000000"));
    }
}
