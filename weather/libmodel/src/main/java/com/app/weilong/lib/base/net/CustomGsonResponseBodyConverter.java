package com.app.weilong.lib.base.net;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * create by weilong on 2020/5/21
 * email: 1436699184@qq.com
 */
public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private  Gson gson;

    private  TypeAdapter<T> adapter;

    private Type type;

    public CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
        this.gson=gson;
        this.adapter=adapter;
        this.type=type;
    }

    
    @Override public T convert(ResponseBody value) throws IOException {
//        String string = value.string();
//        System.out.println("----"+string);
//        BaseResponse httpStatus = gson.fromJson(string, BaseResponse.class);
        //验证status返回是否为1
//        if ("0".equals(httpStatus.getStatus())) {
//            //继续处理body数据反序列化，注意value.string() 不可重复使用
//            MediaType contentType = value.contentType();
//            Charset charset =null;
//            if (contentType!=null){
//                  charset= contentType.charset();
//            }  else {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    charset = UTF_8;
//                }
//            }
//            if (httpStatus.getData()==null || "".equals(httpStatus.getData())){
//                throw new ApiException("1",httpStatus.getMsg());
//            }
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes());
//            InputStreamReader reader = new InputStreamReader(inputStream, charset);
//            JsonReader jsonReader = gson.newJsonReader(reader);
//            try {
//                T result=adapter.read(jsonReader);
//                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
//                    throw new ApiException("1","解析异常!");
//                }
//                return result;
//            }finally {
//                value.close();
//            }
//        } else {
//            value.close();
//            //不为-1，表示响应数据不正确，抛出异常
//           return (T) httpStatus;
//        }


        JsonReader jsonReader = this.gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new ApiException("1","解析异常!");
            }
            return result;
        } finally {
            value.close();
        }
    }
}
