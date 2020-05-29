package com.app.weilong.lib.base.net;



/**
 * create by weilong on 2020/4/9
 * email: 1436699184@qq.com
 *
 * 请求头
 */
public class BaseReqHeads<T> {

//    /**
//     * {
//     * "appid": "cd8024dc280b49d2ad3176cf5a4edc5d",
//     * "format": "json",
//     * "sign": "43021C1D480D0F1478B93F66D9ACF3A3",
//     * "version": "1.0",
//     * "accessToken": "7b1f48e8b52e48318951da9d8013be26",
//     * "nonceStr": "YonHozgpvUeUMjG0pAMEC34APzNXjybS",
//     * "body":"ak2p4U3N1HcDJS9hk/JUWOnjmZe8ACE2IdAO0yXU+WuPFFUhGziRxXHp84S322h3GEuW023gGikC/Dabi68qBbrBolcphC9yueBF0KhOq9aS82s6tt/iV4bHXELJRdA1YUnAZhRhVd5LeeXkCPaqtNAsO2Ip5vkIniOELDmP/ekd57BTAzVYgN911QnWK/0F3joroB+H6qMefoyuZUUhyBaH7EyQNk+JHbA6T6sd8+Yi6fSuorHKWO2Yg67eJhJj"
//     * }
//     */
//    private String appid="4000b707dcdb48f9b35b7c47d1b9af10";
//    private String format="json";
//    private String version= Constants.VERSION;
//    private String key="42c891cb695648659c9a7b00552d701c";
//    private String accessToken= LoginInfoUtils.getAccessToken();
//    private String nonceStr=LoginInfoUtils.getNonceStr();
//
//
//    private String sign;
//    private String authToken=LoginInfoUtils.getAuthToken();
//    private T body;
//
//
//    public BaseReqHeads(){
//        this.appid=ApiService.BASE_APPID;
//        this.format="json";
//        this.version=Constants.VERSION;
//        this.key="42c891cb695648659c9a7b00552d701c";
//        this.accessToken=LoginInfoUtils.getAccessToken();
//        this.nonceStr=LoginInfoUtils.getNonceStr();
//        StringBuilder builder=new StringBuilder();
//        builder.append("accessToken=").append(getAccessToken())
//                .append("&appid=").append(appid)
//                .append("&authToken=").append(LoginInfoUtils.getAuthToken())
//                .append("&format=").append(format)
//                .append("&nonceStr=").append(nonceStr)
//                .append("&version=").append(version)
//                .append("&key=").append(key);
//        this.sign = MD5Utils.parseStrToMd5U32(builder.toString());
//        this.authToken=LoginInfoUtils.getAuthToken();
//    }
//
//
//
//
//    public BaseReqHeads setAuthToken(String authToken) {
//        this.authToken = LoginInfoUtils.getAuthToken();
//        return this;
//    }
//    public BaseReqHeads setAuthToken() {
//        this.authToken = LoginInfoUtils.getAuthToken();
//        return this;
//    }
//
//
//    public String getAuthToken() {
//        return LoginInfoUtils.getAuthToken();
//    }
//
//    public T getBody() {
//        return body;
//    }
//
//    public BaseReqHeads<T> setBody(T body) {
//        this.body = body;
//        return this;
//    }
//
//    public String getAppid() {
//        return "4000b707dcdb48f9b35b7c47d1b9af10";
//    }
//
//    public BaseReqHeads setAppid() {
//        this.appid = "4000b707dcdb48f9b35b7c47d1b9af10";
//        return this;
//    }
//
//    public BaseReqHeads setKey() {
//        this.key = "42c891cb695648659c9a7b00552d701c";
//        return this;
//    }
//
//    public String getKey() {
//        return "42c891cb695648659c9a7b00552d701c";
//    }
//
//    public String getFormat() {
//        return "json";
//    }
//
//    public BaseReqHeads setFormat(String format) {
//        this.format = "json";
//        return this;
//    }
//
//    public String getSign() {
//        StringBuilder builder=new StringBuilder();
//        builder.append("accessToken=").append(getAccessToken())
//                .append("&appid=").append(appid)
//                .append("&authToken=").append(LoginInfoUtils.getAuthToken())
//                .append("&format=").append(format)
//                .append("&nonceStr=").append(nonceStr)
//                .append("&version=").append(version)
//                .append("&key=").append(key);
//        String signMD5 = MD5Utils.parseStrToMd5U32(builder.toString());
//        this.sign = signMD5;
//        return sign;
//    }
//
//    public BaseReqHeads setSign(String sign) {
//
//        StringBuilder builder=new StringBuilder();
//        builder.append("accessToken=").append(getAccessToken())
//                .append("&appid=").append(appid)
//                .append("&authToken=").append(LoginInfoUtils.getAuthToken())
//        .append("&format=").append(format)
//        .append("&nonceStr=").append(nonceStr)
//        .append("&version=").append(version)
//        .append("&key=").append(key);
//        String signMD5 = MD5Utils.parseStrToMd5U32(builder.toString());
//        this.sign = signMD5;
//        return this;
//    }
//
//    public BaseReqHeads setSign() {
//
//        StringBuilder builder=new StringBuilder();
//        builder.append("accessToken=").append(getAccessToken())
//                .append("&appid=").append(appid)
//                .append("&authToken=").append(LoginInfoUtils.getAuthToken())
//                .append("&format=").append(format)
//                .append("&nonceStr=").append(nonceStr)
//                .append("&version=").append(version)
//                .append("&key=").append(key);
//        String signMD5 = MD5Utils.parseStrToMd5U32(builder.toString());
//        this.sign = signMD5;
//        return this;
//    }
//
//    public String getVersion() {
//        return Constants.VERSION;
//    }
//
//    public BaseReqHeads setVersion(String version) {
//        this.version = Constants.VERSION;
//        return this;
//    }
//
//    public String getAccessToken() {
//        return LoginInfoUtils.getAccessToken();
//    }
//
//    public BaseReqHeads setAccessToken(String accessToken) {
//        this.accessToken = LoginInfoUtils.getAccessToken();
//        return this;
//    }
//
//
//    public BaseReqHeads setAccessToken() {
//        this.accessToken = LoginInfoUtils.getAccessToken();
//        return this;
//    }
//
//    public String getNonceStr() {
//        return LoginInfoUtils.getNonceStr();
//    }
//
//    public BaseReqHeads setNonceStr(String nonceStr) {
//        this.nonceStr = LoginInfoUtils.getNonceStr();
//        return this;
//    }


}
