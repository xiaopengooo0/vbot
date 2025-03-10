package com.xiao.vbot.listener;

import com.xiao.vbot.common.dto.login.res.CheckLoginResponse;
import com.xiao.vbot.common.dto.login.res.QrResponse;
import com.xiao.vbot.config.GeweProperty;
import com.xiao.vbot.gewe.event.LoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: xiaopeng
 * @Description: 设置登录事件监听器
 * @DateTime: 2025/3/10 下午4:07 星期一
 **/
@Component
public class LoginEventListener implements ApplicationListener<LoginEvent> {
    private static final Logger log = LoggerFactory.getLogger(LoginEventListener.class);
    @Resource
    private GeweProperty geweProperty;
    @Override
    public void onApplicationEvent(LoginEvent event) {
        CheckLoginResponse loginResponse = (CheckLoginResponse) event.getSource();
        geweProperty.setUuid(loginResponse.getUuid());
        log.info("获取二维码成功,wxid:{}", loginResponse.getNickName());

    }
}
