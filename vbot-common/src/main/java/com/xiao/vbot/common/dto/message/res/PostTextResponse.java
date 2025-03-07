package com.xiao.vbot.common.dto.message.res;

import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午5:17 星期二
 **/
@Data
public class PostTextResponse {
    /**
     * 发送时间
     */
    private long createTime;
    /**
     * 消息ID
     */
    private long msgId;
    /**
     * 消息ID
     */
    private long newMsgId;
    /**
     * 接收人的wxid
     */
    private String toWxid;
    /**
     * 消息类型
     */
    private long type;
}
