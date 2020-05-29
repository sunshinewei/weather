package com.app.weilong.lib.base.basemvp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;


import com.app.weilong.lib.R;

import java.io.File;


public abstract class BaseMvpActivity extends BaseActivity implements BaseView{
    private ProxyActivity proxyActivity;


    @Override
    public void initBaseData(@Nullable Bundle savedInstanceState) {
        proxyActivity=createProxyCreate();
        proxyActivity.bindPresenter();
        initData(savedInstanceState);

    }
    public abstract void initData(@Nullable Bundle savedInstanceState);

    private ProxyActivity createProxyCreate(){

        if (proxyActivity==null){
            return new ProxyActivity(this);
        }
        return proxyActivity;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        proxyActivity.unBindPresenter();
    }


    /**
     * 选择相机
     */
    protected void selectPhoto(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                requestPermission(Manifest.permission.CAMERA, "sssss", Constants.PERMISSSION_REQ_CODE);
            } else {
                takePhoto(mContext);
            }
        } else {
            takePhoto(mContext);
        }
    }

    /**
     * 选择图片库
     */
    protected void selectPicture(int number){
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    getString(R.string.permission_write_storage_rationale),
                    REQUEST_STORAGE_WRITE_ACCESS_PERMISSION);

        }else {
            //选择图片
//            Matisse.from((Activity) mContext)
//                    .choose(MimeType.ofImage())
//                    .countable(true)
//                    .maxSelectable(number)
////                                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                    .thumbnailScale(0.85f)
//                    .imageEngine(new GlideEngine())
//                    .showPreview(true) // Default is `true`
//                    .forResult(REQUEST_CODE_CHOOSE);
        }
    }

    /**
     * 位置
     */
    protected void permissionGranted(){

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted();
                }
                break;
//            case Constants.PERMISSSION_REQ_CODE:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    takePhoto(mContext);
//                }
//                break;
//            case REQUEST_STORAGE_WRITE_ACCESS_PERMISSION:
//                //选择图片
//                Matisse.from((Activity) mContext)
//                        .choose(MimeType.ofImage())
//                        .countable(true)
//                        .maxSelectable(4)
////                                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
//                        .thumbnailScale(0.85f)
//                        .imageEngine(new GlideEngine())
//                        .showPreview(true) // Default is `true`
//                        .forResult(REQUEST_CODE_CHOOSE);
//                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    /**
     * 拍照
     */
    protected File mTempPhotoPath;
    protected void takePhoto(Context mContext){
        // 跳转到系统的拍照界面
        Intent intentToTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定照片存储位置为sd卡本目录下
        // 这里设置为固定名字 这样就只会只有一张temp图 如果要所有中间图片都保存可以通过时间或者加其他东西设置图片的名称
        // File.separator为系统自带的分隔符 是一个固定的常量
        mTempPhotoPath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        // 获取图片所在位置的Uri路径    *****这里为什么这么做参考问题2*****
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(mContext, "com.app.longguan.property.my.provider",
                    mTempPhotoPath);
            intentToTakePhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentToTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        }else {
            Uri imageUri = Uri.fromFile(mTempPhotoPath);
            intentToTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }

        //下面这句指定调用相机拍照后的照片存储的路径
//        startActivityForResult(intentToTakePhoto, Constants.RequestMode);

    }

}
