package com.app.weilong.lib.base.view;


import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;

import com.app.weilong.lib.R;


public class ToastUtil {

    private static Toast mToast;
    static View inflateRoot;


    public static Toast showToast(Context mCtx, String msg) {
        if (mToast == null) {
            mToast = new Toast(mCtx.getApplicationContext());
        }

        if (inflateRoot == null) {
            inflateRoot = View.inflate(mCtx.getApplicationContext(), R.layout.toast_view, null);
        }
        TextView tv = inflateRoot.findViewById(R.id.tv_text);
        tv.setText(msg);
        mToast.setView(inflateRoot);
        return mToast;
    }

    public Toast showToast(Context mCtx, @IdRes int resId) {
        if (mToast == null) {
            mToast = new Toast(mCtx.getApplicationContext());
        }

        if (inflateRoot == null) {
            inflateRoot = View.inflate(mCtx, R.layout.toast_view, null);
        }

        TextView tv = inflateRoot.findViewById(R.id.tv_text);

        String resourceName = mCtx.getResources().getResourceName(resId);

        tv.setText(resourceName);
        mToast.setView(inflateRoot);
        return mToast;
    }


}
