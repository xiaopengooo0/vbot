package com.xiao.vbot.common.dto.message.res;

import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午5:25 星期二
 **/
@Data
public class PostImageResponse {
    /**
     * cdn相关的aeskey
     */
    private String aesKey;
    /**
     * 发送时间
     */
    private long createTime;
    /**
     * cdn相关的fileid
     */
    private String fileId;
    /**
     * 图片高度
     */
    private long height;
    /**
     * 图片文件大小
     */
    private long length;
    /**
     * 图片md5
     */
    private String md5;
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
    private Object type;
    /**
     * 图片宽度
     */
    private long width;
}
