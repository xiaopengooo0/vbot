package com.xiao.vbot.service.core;

import com.alibaba.fastjson.JSONObject;

import com.xiao.vbot.common.dto.login.req.CheckLoginDto;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.common.dto.BaseDto;
import com.xiao.vbot.common.dto.login.req.CallbackDto;
import com.xiao.vbot.common.dto.login.res.CheckLoginResponse;
import com.xiao.vbot.common.dto.login.res.QrResponse;

import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: 用于完成自定义事件封装
 * @DateTime: 2025/3/7 上午11:52 星期五
 **/
public interface ILoginService {
    /**
     * 获取token
     * @return
     */
    Response<String> getToken() throws IOException;


    /**
     * 设置回调地址
     * @param callback
     * @return
     */
    Response setCallback(CallbackDto callback) throws IOException;

    /**
     * 获取登录二维码
     * @return
     */
    Response<QrResponse> getQr() throws IOException;

    /**
     * 确认登陆
     * @param param
     * @return
     */
    Response<CheckLoginResponse> checkLogin(CheckLoginDto param) throws IOException;

    /**
     * 退出微信
     * @param param
     * @return
     */
    Response logout(BaseDto param) throws IOException;

    /**
     * 弹框登录
     *
     * @param param
     * @return
     */
    JSONObject dialogLogin(BaseDto param) throws IOException;

    /**
     * 检查是否在线
     * @param param
     * @return
     */
    Response<Boolean> checkOnline(BaseDto param) throws IOException;
}
