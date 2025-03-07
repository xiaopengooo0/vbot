package com.xiao.vbot.controller;

import com.xiao.vbot.common.dto.login.req.CheckLoginDto;
import com.xiao.vbot.common.exception.GeweApiException;
import com.xiao.vbot.common.res.Response;

import com.xiao.vbot.common.dto.BaseDto;
import com.xiao.vbot.common.dto.login.res.CheckLoginResponse;
import com.xiao.vbot.common.dto.login.res.QrResponse;
import com.xiao.vbot.service.ILoginService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

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
    private ILoginService loginService;


    @PostMapping("/getQrCode")
    public Response<QrResponse> getQrCode() {
        try {
            return loginService.getQr();
        } catch (IOException e) {
            throw new GeweApiException(e);
        }
    }


    @PostMapping("/checkLogin")
    public Response<CheckLoginResponse> checkLogin(@RequestBody CheckLoginDto CheckLoginDto) {
        try {
            return loginService.checkLogin(CheckLoginDto);
        } catch (IOException e) {
            throw new GeweApiException(e);
        }
    }
}
