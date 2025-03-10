package com.xiao.vbot.gewe.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: xiaopeng
 * @Description: 登录事件
 * @DateTime: 2025/3/10 下午4:05 星期一
 **/
public class LoginEvent extends ApplicationEvent {
    public LoginEvent(Object source) {
        super(source);
    }
}


