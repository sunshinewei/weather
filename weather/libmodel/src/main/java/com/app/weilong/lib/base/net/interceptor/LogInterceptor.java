package com.app.weilong.lib.base.net.interceptor;

import android.content.Intent;



import com.app.weilong.lib.base.utils.LogUtils;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept( Chain chain) throws IOException {

        Request request = chain.request();
        Request requestHead = request.newBuilder()
                .header("deviceType", "xiaomi")//添加设备
                .build();
        LogUtils.debug("request----------"+request.url().toString());
        LogUtils.error("request----------"+request.headers().toString());
        Response proceed = chain.proceed(requestHead);
        String body = proceed.body().string();
        System.out.println(body);
        return proceed.newBuilder()
                .body(ResponseBody.create(MediaType.parse("UTF-8"), body))
                .build();

//        return proceed;

//        Response response = chain.proceed(requestHead);
//        return new Response.Builder()
//                .body(newResponseBody(response))
//                .request(response.request())
//                .protocol(response.protocol())
//                .build();
    }




}
