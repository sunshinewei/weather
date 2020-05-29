package com.app.weilong.weather;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.app.weilong.lib.base.basemvp.BaseMvpActivity;
import com.app.weilong.lib.base.basemvp.InjectPresenter;
import com.app.weilong.lib.base.utils.LogUtils;
import com.app.weilong.weather.activity.MainMContract;
import com.app.weilong.weather.activity.MainPresenter;
import com.app.weilong.weather.apply.LocationUtils;
import com.app.weilong.weather.bean.WeatherInfoBean;
import com.app.weilong.weather.listener.LocationListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends BaseMvpActivity implements MainMContract.MainView {
    private TextView tvContent;



    @InjectPresenter
    MainPresenter presenter;
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.PERMISSSION_REQ_CODE);//自定义的code
        }else {
            locationInfo();
        }


    }

    @Override
    protected void permissionGranted() {
           locationInfo();
    }


    private void locationInfo(){
        LocationUtils
                .location(mContext, new LocationListener() {
                    @Override
                    public void result(AMapLocation aMapLocation) {
                        if (aMapLocation != null) {
                            if (aMapLocation.getErrorCode() == 0) {
                                //定位成功回调信息，设置相关消息
                                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                                aMapLocation.getLatitude();//获取纬度
                                aMapLocation.getLongitude();//获取经度
                                aMapLocation.getAccuracy();//获取精度信息
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date = new Date(aMapLocation.getTime());
                                df.format(date);//定位时间
                                loadingDialog();
                                presenter.weatherInfo("3fe72c013196054857b8a2e9f5d86ba8",aMapLocation.getAdCode(),"");

                            } else {
                                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                                LogUtils.error("location Error, ErrCode:"
                                        + aMapLocation.getErrorCode() + ", errInfo:"
                                        + aMapLocation.getErrorInfo());
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    public void weatherInfoView(WeatherInfoBean bean) {
        loadingOnSuccess();
        ArrayList<WeatherInfoBean.LivesBean> lives = bean.getLives();
        tvContent.setText(lives.get(0).getProvince()+"\n"+lives.get(0).getWeather()+"\n"+lives.get(0).getWinddirection());

    }

    @Override
    public void fail(String msg) {
       LoadingFail();
       showBaseToast(msg);
    }
}
