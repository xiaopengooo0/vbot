 package com.xiao.vbot.gewe.api;

import com.alibaba.fastjson2.JSONObject;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.gewe.dto.BaseDto;
import com.xiao.vbot.gewe.dto.login.req.CallbackDto;
import com.xiao.vbot.gewe.dto.login.res.CheckLoginResponse;
import com.xiao.vbot.gewe.dto.login.res.QrResponse;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author: xiaopeng
 * @Description: 登录接口
 * @DateTime: 2025/3/4 下午3:51 星期二
 **/
public interface ILoginApi {

    /**
     * 获取token
     * @return
     */
    @POST("/tools/getTokenId")
    Call<Response<String>> getToken();


    /**
     * 设置回调地址
     * @param callback
     * @return
     */
    @POST("/tools/setCallback")
    Call<Response> setCallback(@Body CallbackDto callback);

    /**
     * 获取登录二维码
     * @param param
     * @return
     */
    @POST("/login/getLoginQrCode")
    Call<Response<QrResponse>> getQr(@Body BaseDto param);

    /**
     * 确认登陆
     * @param param
     * @return
     */
    @POST("/login/checkLogin")
    Call<Response<CheckLoginResponse>> checkQr(@Body BaseDto param);

    /**
     * 退出微信
     * @param param
     * @return
     */
    @POST("/login/logout")
    Call<Response> logOut(@Body BaseDto param);

    /**
     * 弹框登录
     * @param param
     * @return
     */
    @POST("/login/dialogLogin")
    Call<JSONObject> dialogLogin(@Body BaseDto param);

    /**
     * 检查是否在线
     * @param param
     * @return
     */
    @POST("/login/checkOnline")
    Call<Response<Boolean>> checkOnline(@Body BaseDto param);

}




