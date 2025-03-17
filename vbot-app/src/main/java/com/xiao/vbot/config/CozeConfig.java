package com.xiao.vbot.config;

import com.coze.openapi.service.auth.TokenAuth;
import com.coze.openapi.service.config.Consts;
import com.coze.openapi.service.service.CozeAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xiaopeng
 * @Description: coze 配置
 * @DateTime: 2025/3/17 下午4:42 星期一
 **/
@Configuration
public class CozeConfig {

    @Value("${coze.token}")
    private String cozeToken;

    @Bean
    public CozeAPI cozeApi() {
       return new CozeAPI.Builder()
                .baseURL(Consts.COZE_CN_BASE_URL)
                .auth(new TokenAuth(cozeToken))
                .readTimeout(10000)
                .build();
    }
}
