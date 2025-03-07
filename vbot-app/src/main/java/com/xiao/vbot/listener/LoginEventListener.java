package com.xiao.vbot.listener;

import com.xiao.vbot.common.dto.login.res.QrResponse;
import com.xiao.vbot.config.GeweProperty;
import com.xiao.vbot.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/7 下午2:10 星期五
 **/
@Component
public class LoginEventListener implements ApplicationListener<LoginEvent> {

    private static  final Logger logger = LoggerFactory.getLogger(LoginEventListener.class);

    @Resource
    private GeweProperty geweProperty;
    @Override
    public void onApplicationEvent(LoginEvent event) {
        QrResponse body = (QrResponse) event.getSource();
        geweProperty.setUuid(body.getUuid());
        geweProperty.setAppId(body.getAppId());
        logger.debug("获取二维码成功,uuid:{},appid:{}", body.getUuid(), body.getAppId());
    }
}
