package com.xiao.vbot.gewe.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.login.req.CheckLoginDto;
import com.xiao.vbot.common.exception.GeweApiException;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.gewe.api.ILoginApi;
import com.xiao.vbot.common.dto.BaseDto;
import com.xiao.vbot.common.dto.login.req.CallbackDto;
import com.xiao.vbot.common.dto.login.res.CheckLoginResponse;
import com.xiao.vbot.common.dto.login.res.QrResponse;
import com.xiao.vbot.service.ILoginService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: login service
 * @DateTime: 2025/3/7 上午11:55 星期五
 **/
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private ILoginApi loginApi;


    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Override
    public Response<String> getToken() throws IOException {
        return loginApi.getToken().execute().body();
    }

    @Override
    public Response setCallback(CallbackDto callback) throws IOException {
        return loginApi.setCallback(callback).execute().body();
    }

    @Override
    public Response<QrResponse> getQr() throws IOException {
            Call<Response<QrResponse>> qr = loginApi.getQr(new BaseDto());
            retrofit2.Response<Response<QrResponse>> response = qr.execute();
            if (response.isSuccessful()) {
                Response<QrResponse> body = response.body();
                if (body != null && body.getRet() == 200) {
                    eventPublisher.publishEvent(body.getData());
                }
                return body;
            } else {
                // 处理非成功的响应
                throw new GeweApiException("Failed to get QR code: " + response.code());
            }




    }

    @Override
    public Response<CheckLoginResponse> checkLogin(CheckLoginDto param) throws IOException {
        return loginApi.checkLogin(param).execute().body();
    }

    @Override
    public Response logout(BaseDto param) throws IOException {
        return loginApi.logOut(param).execute().body();
    }

    @Override
    public JSONObject dialogLogin(BaseDto param) throws IOException {
        return loginApi.dialogLogin(param).execute().body();
    }

    @Override
    public Response<Boolean> checkOnline(BaseDto param) throws IOException {
        return loginApi.checkOnline(param).execute().body();
    }
}
