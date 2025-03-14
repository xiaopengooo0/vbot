package com.xiao.vbot.gewe.mapper;

import com.mongoplus.mapper.MongoMapper;
import com.xiao.vbot.gewe.entity.Message;

import java.util.List;

/**
 * @Author: xiaopeng
 * @Description:  MessageMapper
 * @DateTime: 2025/3/14 上午11:42 星期五
 **/
public interface MessageMapper extends MongoMapper<Message> {

    /**
     * 根据wxid查询历史消息
     * @param wxid
     * @return
     */
    List<Message> findMessages(String wxid);

    /**
     * 查询聊天室历史消息
     * @param room
     * @return
     */
    List<Message> findMessagesInRoom(String room);


    Message findMessageByMsgId(String newMsgId);
}
