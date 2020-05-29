package com.app.weilong.weather.net;

import com.app.weilong.lib.base.MyApplication;
import com.app.weilong.lib.base.net.ApiManager;
import com.app.weilong.lib.base.net.ApiService;
import com.app.weilong.lib.base.net.ImpRetrofitClient;
import com.app.weilong.lib.base.utils.NetUtils;
import com.app.weilong.lib.base.view.ToastUtil;

/**
 * create by weilong on 2020/5/26
 * email: 1436699184@qq.com
 */
public class ImRetrofitClient extends ImpRetrofitClient {

    public static ImRetrofitClient mInstance;

    public ServiceApi mApiManager;
    private ImRetrofitClient(String Url){
        super(Url);
        mApiManager = retrofitBuild.create(ServiceApi.class);
    }

    public static ImRetrofitClient newInstance(int ... state){//state 1.支付，2订单详情,3 物业 4.文件上传 5.实名认证

        if (!NetUtils.isNetWork(MyApplication.getAppConext())){
            ToastUtil.showToast(MyApplication.getAppConext(),"当前无网络，请您检查网络环境！").show();
        }


        if (state.length==0){
            synchronized (ImpRetrofitClient.class){
                return new ImRetrofitClient(ApiService.BASE_API);
            }
        }else {
            int i = state[0];
            if (i==1){
                synchronized (ImpRetrofitClient.class){
                    return new ImRetrofitClient(ApiService.BASE_PAY_API);
                }
            }else if (i==2){
                synchronized (ImpRetrofitClient.class){
                    return new ImRetrofitClient(ApiService.BASE_ORDER_API);
                }
            }else if (i==3){//物业
                synchronized (ImpRetrofitClient.class){
                    return new ImRetrofitClient(ApiService.BASE_ESTATE_API);
                }
            }else if (i==4){//图片
                synchronized (ImpRetrofitClient.class){
                    return new ImRetrofitClient(ApiService.BASE_FILE_API);
                }
            }else if (i==5){  //实名认证
                synchronized (ImpRetrofitClient.class){
                    return new ImRetrofitClient(ApiService.BASE_REALNAME_API);
                }
            }
        }
//        if (mInstance==null){
//            synchronized (ImpRetrofitClient.class){
//                if (mInstance==null){
//                    return new ImpRetrofitClient(ApiService.BASE_API);
//                }
//            }
//        }
        return mInstance;
    }

}
