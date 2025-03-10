package com.xiao.vbot.gewe.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: xiaopeng
 * @Description: 二维码扫描事件
 * @DateTime: 2025/3/7 下午2:11 星期五
 **/
public class QrEvent extends ApplicationEvent {
    public QrEvent(Object source) {
        super(source);
    }
}
