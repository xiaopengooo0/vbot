package com.xiao.vbot.gewe.entity;

import com.mongoplus.annotation.ID;
import com.mongoplus.annotation.collection.CollectionField;
import com.mongoplus.annotation.collection.CollectionName;
import com.mongoplus.annotation.index.MongoIndex;
import com.xiao.vbot.common.dto.callback.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/14 上午10:43 星期五
 **/
@Data
@CollectionName("message")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @ID
    private String id;


    @CollectionField("TypeName")
    private String typeName;

    @CollectionField("Appid")
    private String appid;

    @CollectionField("Wxid")
    private String wxid;

    @CollectionField("Data")
    private DataEntity data;


    @CollectionField("reply")
    private Document reply;

    public String getNewMsgId() {
        return data.getNewMsgId();
    }

    public String getFromUser() {
        return data.getFromUserName().getString();
    }

    public Object getCreateTime() {
        return data.getCreateTime();
    }


    @Data
    public static class DataEntity {
        @CollectionField("Content")
        private ContentEntity contentEntity;

        @CollectionField("CreateTime")
        private String createTime;

        @CollectionField("FromUserName")
        private UserName fromUserName;

        @CollectionField("ImgBuf")
        private Document imgBuf;

        @CollectionField("ImgStatus")
        private int imgStatus;

        @CollectionField("MsgId")
        private String msgId;

        @CollectionField("MsgSeq")
        private String msgSeq;

        @CollectionField("MsgSource")
        private String msgSource;

        @CollectionField("MsgType")
        private int msgType;
        @MongoIndex(unique=true)
        @CollectionField("NewMsgId")
        private String newMsgId;

        @CollectionField("PushContent")
        private String pushContent;

        @CollectionField("Status")
        private int status;

        @CollectionField("ToUserName")
        private UserName toUserName;
    }

    @Data
    public static class ContentEntity {
        @CollectionField("string")
        private String string;
    }
}
