package com.xiao.vbot.controller;

import com.xiao.vbot.common.exception.GeweApiException;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.config.GeweProperty;
import com.xiao.vbot.gewe.api.ILoginApi;
import com.xiao.vbot.gewe.dto.BaseDto;
import com.xiao.vbot.gewe.dto.login.res.CheckLoginResponse;
import com.xiao.vbot.gewe.dto.login.res.QrResponse;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: 登录控制器
 * @DateTime: 2025/3/5 下午4:36 星期三
 **/
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);


    @Resource
    private ILoginApi loginApi;

    @Resource
    private GeweProperty geweProperty;



    @PostMapping("/getQrCode")
    public Response<QrResponse> getQrCode() {
        try {
            Call<Response<QrResponse>> qr = loginApi.getQr(new BaseDto());
            retrofit2.Response<Response<QrResponse>> response = qr.execute();
            if (response.isSuccessful()) {
                Response<QrResponse> body = response.body();
                if (body != null && body.getRet() == 200) {
                    geweProperty.setUuid(body.getData().getUuid());
                    geweProperty.setAppId(body.getData().getAppId());
                    logger.debug("获取二维码成功,uuid:{},appid:{}", body.getData().getUuid(), body.getData().getAppId());
                }
                return body;
            } else {
                // 处理非成功的响应
                throw new GeweApiException("Failed to get QR code: " + response.code());
            }
        } catch (IOException e) {
            throw new GeweApiException(e);
        }
    }


    @PostMapping("/checkLogin")
    public Response<CheckLoginResponse> checkLogin() {
        Call<Response<CheckLoginResponse>> checkLogin = loginApi.checkLogin(new BaseDto());
        try {
            return checkLogin.execute().body();
        } catch (IOException e) {
            throw new GeweApiException(e);
        }
    }
}
