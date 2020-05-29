package com.app.weilong.lib.base.utils;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.widget.ImageView;


import com.app.weilong.lib.base.MyApplication;
import com.app.weilong.lib.base.view.GlideRoundTransform;
import com.app.weilong.lib.base.view.circleview.GlideCircleTransform;
import com.app.weilong.lib.base.view.circleview.GlideCircleTransformWithBorder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * 图片加载库
 */
public class GlideUtils {


    /**
     * 个人中心图标显示
     * @param mctx
     * @param url
     * @param img
     *
     * fitCenter()
     */
    public static void loadGlide(Context mctx, String url, ImageView img){
        if (url==null){
            return;
        }
        Glide.with(mctx)
                .load(url)
               
                .fitCenter()
                .into(img);
    }

    public static void loadGlideComm(Context mctx, String url, ImageView img){
        if (url==null){
            return;
        }
        Glide.with(mctx)
                .load(url)
                .into(img);
    }

    /**
     * 有占位
     * @param mctx
     * @param url
     * @param img
     */
    public static void loadGlidepLaceHolderHead(Context mctx, String url, ImageView img){
        Glide.with(mctx)
                .load(url)
                .centerCrop()
             
                .into(img);
    }



    /**
     * 有占位 大圆弧
     * @param mctx
     * @param url
     * @param img
     */
    public static void loadGlidepLaceHolder(Context mctx, String url, ImageView img){
        Glide.with(MyApplication.getAppConext())
                .load(url).dontAnimate()

                .transform(new GlideRoundTransform(mctx))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(img);
    }

    public static void loadGlideRaduis78(Context mctx, String url, ImageView img){
        Glide.with(MyApplication.getAppConext())
                .load(url)
                .override(78,78)
                .centerCrop()

                .transition(DrawableTransitionOptions.withCrossFade())//淡入
                .transform(new GlideRoundTransform(mctx))
                .into(img);
    }





    public static void loadGlideUri(Context mctx, Uri url, ImageView img){
        Glide.with(mctx)
                .load(url)
                .centerCrop()

                .into(img);
    }




    //Glide加载图片为圆形
    public static void loadCircleImageView(Context context, String url, ImageView iv, boolean isShowFrame, int color) {
        if (context != null) {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed()) {
                        if (isShowFrame) {
                            loadCircleBorder(context, url, iv, color);
                        } else {
                            loadCircle(context, url, iv);
                        }
                    }
                } else {
                    if (isShowFrame) {
                        loadCircleBorder(context, url, iv, color);
                    } else {
                        loadCircle(context, url, iv);
                    }
                }
            } else {
                if (isShowFrame) {
                    loadCircleBorder(context, url, iv, color);
                } else {
                    loadCircle(context, url, iv);
                }
            }
        }
    }

    private static void loadCircle(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(iv);
    }

    private static void loadCircleBorder(Context context, String url, ImageView iv, int color) {
        Glide.with(context).load(url).
                diskCacheStrategy(DiskCacheStrategy.RESOURCE).
                transform(new GlideCircleTransformWithBorder(context, 1, color)).
                into(iv);
    }

}