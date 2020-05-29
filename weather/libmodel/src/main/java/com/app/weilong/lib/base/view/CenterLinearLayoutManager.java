package com.app.weilong.lib.base.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.app.weilong.lib.base.utils.LogUtils;


/**
 * create by weilong on 2020/5/20
 * email: 1436699184@qq.com
 */
public class CenterLinearLayoutManager extends LinearLayoutManager {
    public CenterLinearLayoutManager(Context context) {
        super(context);
    }

    public CenterLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        RecyclerView.SmoothScroller  smoothScroll = new CenterSmoothScroll(recyclerView.getContext());
        smoothScroll.setTargetPosition(position);
        startSmoothScroll(smoothScroll);
    }



    private class CenterSmoothScroll extends LinearSmoothScroller {


        public CenterSmoothScroll(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {

            LogUtils.error("-----------"+viewStart+"===="+viewEnd+"   "+boxStart+"-----"+boxEnd+"-----"+snapPreference);
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
    }


    
}
