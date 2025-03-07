package com.xiao.vbot.common.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiao.vbot.common.constants.MsgType;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/7 下午5:05 星期五
 **/
@Data
public class WeChatMessage implements Serializable {
    /**
     * 消息类型
     */
    @JsonProperty("TypeName")
     private String typeName = "AddMsg";
    /**
     * 设备id
     */
     @JsonProperty("Appid")
     private String  appid  ;
    /**
     * 所属微信的wxid
     */
    @JsonProperty("Wxid")
     private String wxid;

     @JsonProperty("Data")
     private MessageDetail data;
}

@Data
class MessageDetail {
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
    @JsonProperty("MsgType")
    private MsgType msgType;
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
    @JsonProperty("imgBuf")
    private ImgBuf ImgBuf;
    @JsonProperty("CreateTime")
    private long createTime;
    @JsonProperty("msgSource")
    private String MsgSource;
    /**
     * 推送内容
     */
    @JsonProperty("PushContent")
    private String pushContent;
    /**
     * 新消息id
     */
    @JsonProperty("newMsgId")
    private long NewMsgId;
    @JsonProperty("MsgSeq")
    private long msgSeq;

    // Getters and Setters
}
@Data
class UserName {
    private String string;
}
@Data
class Content {
    private String string;
}
@Data
class ImgBuf {
    private int iLen;
}

