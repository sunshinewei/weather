package com.app.weilong.lib.base.file;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * create by weilong on 2020/4/28
 * email: 1436699184@qq.com
 */
public class FileUpload {


//    public static void upLoad(List<File> files, String userZone, String module,
//                              String channelFlag, ResultCallBack callBack){
//        BaseReqHeads baseReqHeads=new BaseReqHeads();
//        ImpRetrofitClient
//                .newInstance(4)
//                .mApiManager
//                .photoUpload(ReqFileHeadUtils.filesToMultipartBody(files,userZone,module
//                        ,baseReqHeads.getSign(),channelFlag))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<FileBean>()
//                        .setmCallBackImp(new CallBackImp<FileBean>() {
//                            @Override
//                            public void onSuccess(FileBean baseResponse) {
//                                callBack.onSuccess(baseResponse);
//                            }
//
//                            @Override
//                            public void onError(String msg) {
//                                callBack.onError(msg);
//                            }
//                        }));
//    }

}
