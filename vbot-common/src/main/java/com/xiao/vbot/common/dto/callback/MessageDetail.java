package com.xiao.vbot.common.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageDetail {
    /**
     * 消息id
     */
    @JsonProperty("MsgId")
    private long msgId;
    /**
     * 发送者
     */
    @JsonProperty("FromUserName")
    private UserName fromUserName;
    /**
     * 接收者
     */
    @JsonProperty("ToUserName")
    private UserName toUserName;
    /**
     * 消息类型
     */
    @JsonProperty(value = "MsgType")
    private int msgType;
    /**
     * 消息内容
     */
    @JsonProperty("Content")
    private Content content;
    /**
     * 状态
     */
    @JsonProperty("Status")
    private int status;
    /**
     * 图片状态
     */
    @JsonProperty("ImgStatus")
    private int imgStatus;
    /**
     * 图片buf
     *
     * **/
    @JsonProperty("ImgBuf")
    private ImgBuf ImgBuf;
    @JsonProperty("CreateTime")
    private long createTime;
    @JsonProperty("MsgSource")
    private String MsgSource;
    /**
     * 推送内容
     */
    @JsonProperty("PushContent")
    private String pushContent;
    /**
     * 新消息id
     */
    @JsonProperty("NewMsgId")
    private long NewMsgId;
    @JsonProperty("MsgSeq")
    private long msgSeq;

}
