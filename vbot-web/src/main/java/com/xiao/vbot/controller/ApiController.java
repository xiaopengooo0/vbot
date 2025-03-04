package com.xiao.vbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiaopeng
 * @Description: 
 * @DateTime: 2025/3/4 下午5:31 星期二
 **/
@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @PostMapping("/callback")
    public void callback(HttpServletRequest request) {
        //查看参数结构
        request.getParameterMap().forEach((k, v) -> {
            logger.info("请求key{} : {}", k, v);
        });

    }
}
