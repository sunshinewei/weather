/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.app.weilong.lib.base.view.camera.util;

import android.content.res.Resources;

public class DimensionUtil {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
