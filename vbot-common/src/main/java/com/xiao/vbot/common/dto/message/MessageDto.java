package com.xiao.vbot.common.dto.message;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/14 下午2:56 星期五
 **/
@Data
public class MessageDto {

    private WeChatMessage message;
    private ReplyDto reply;
}
