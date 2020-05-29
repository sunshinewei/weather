package com.app.weilong.lib.base.file;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.app.weilong.lib.base.net.BaseReqHeads;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * create by weilong on 2020/4/28
 * email: 1436699184@qq.com
 */
public class ReqFileHeadUtils {

    public static MultipartBody filesToMultipartBody(List<File> files,String userZone,String module,
                                                     String sign,String channelFlag) {

        MultipartBody.Builder builder = new MultipartBody.Builder();

        BaseReqHeads reqHeads = new BaseReqHeads<>();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
//        builder.addFormDataPart("appid", ApiService.BASE_APPID);
//        builder.addFormDataPart("userZone", userZone);
//        builder.addFormDataPart("module", module);
//        builder.addFormDataPart("sign", reqHeads.getSign());
//        builder.addFormDataPart("channelFlag", channelFlag);
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }


    public static File uri2File(Uri uri, Activity mAct) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = mAct.managedQuery(uri, proj, null,
                null, null);
        System.out.println("-----------------------"+actualimagecursor.toString());
        if (actualimagecursor == null) {
            img_path = uri.getPath();
            System.out.println("-----------------------"+img_path);
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            System.out.println("-----------------------"+actual_image_column_index);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }

}
