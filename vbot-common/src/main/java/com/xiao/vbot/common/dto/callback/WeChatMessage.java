package com.xiao.vbot.common.dto.callback;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xiao.vbot.common.constants.MsgType;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @Author: xiaopeng
 * @Description: 微信回调消息
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



