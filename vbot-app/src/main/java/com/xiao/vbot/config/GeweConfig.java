package com.xiao.vbot.config;


import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.common.exception.GeweApiException;
import com.xiao.vbot.config.properties.GeweProperty;
import com.xiao.vbot.gewe.api.ILoginApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: 初始化 token
 * @DateTime: 2025/3/4 下午2:57 星期二
 **/
@Component
public class GeweConfig {

    private static final Logger log = LoggerFactory.getLogger(GeweConfig.class);
    @Resource
    private ILoginApi loginApi;

    @Resource
    private GeweProperty geweProperty;

    @PostConstruct
    public void init() {
        try {
            Response<String> response = loginApi.getToken().execute().body();

            if (response != null && response.getRet() == 200) {
                log.info("项目初始化=============获取token:{}===============", response.getData());
                geweProperty.setToken(response.getData());
            }else{
                throw new GeweApiException("获取token失败");
            }

        } catch (IOException e) {
            throw new GeweApiException(e);
        }
    }

}
