package com.xiao.vbot.service.core;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.common.dto.message.MessageDto;

import java.util.List;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/14 下午2:13 星期五
 **/
public interface IMessageRepository  {

     void save(MessageDto message);

     List<String> getMessages(String id);

}
