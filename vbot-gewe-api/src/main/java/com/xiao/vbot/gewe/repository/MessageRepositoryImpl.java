package com.xiao.vbot.gewe.repository;

import com.alibaba.fastjson.JSONObject;
import com.mongoplus.conditions.query.LambdaQueryChainWrapper;
import com.mongoplus.mapper.MongoMapperImpl;
import com.mongoplus.model.PageResult;
import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.UserName;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.common.dto.message.MessageDto;
import com.xiao.vbot.common.dto.message.ReplyDto;
import com.xiao.vbot.gewe.entity.Message;
import com.xiao.vbot.gewe.mapper.MessageMapper;
import com.xiao.vbot.service.core.IMessageRepository;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/14 上午11:48 星期五
 **/
@Repository
public class MessageRepositoryImpl extends MongoMapperImpl<Message> implements MessageMapper, IMessageRepository {

    @Override
    public List<Message> findMessages(String wxid) {

        PageResult<Message> messages = new LambdaQueryChainWrapper<>(this.getBaseMapper(), Message.class).eq(Message::getWxid, wxid).page(1, 10);
        if (messages.getTotalSize() > 0) {
            return messages.getContentData();
        }
        return List.of();
    }

    @Override
    public List<Message> findMessagesInRoom(String room) {
        return List.of();
    }

    @Override
    public Message findMessageByMsgId(String newMsgId) {
        if (newMsgId != null) {
            return new LambdaQueryChainWrapper<>(this.getBaseMapper(), Message.class).eq(Message::getNewMsgId, newMsgId).one();
        }
        return null;
    }

    @Override
    public void save(MessageDto messageDto) {
        //类型转换
        Message message = new Message();
        WeChatMessage weChatMessage = messageDto.getMessage();
        message.setAppid(weChatMessage.getAppid());
        message.setWxid(weChatMessage.getWxid());
        message.setTypeName(weChatMessage.getTypeName());
        MessageDetail detail = weChatMessage.getData();
        Message.DataEntity dataEntity = new Message.DataEntity();
        Message.ContentEntity contentEntity = new Message.ContentEntity();
        contentEntity.setString(detail.getContent().getString());
        dataEntity.setContentEntity(contentEntity);
        dataEntity.setCreateTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(detail.getCreateTime()), ZoneId.systemDefault()).toString());
        dataEntity.setFromUserName(detail.getFromUserName());
        dataEntity.setMsgId(String.valueOf(detail.getMsgId()));
        dataEntity.setMsgSeq(String.valueOf(detail.getMsgSeq()));
        dataEntity.setMsgSource(detail.getMsgSource());
        dataEntity.setMsgType(detail.getMsgType());
        dataEntity.setNewMsgId(String.valueOf(detail.getNewMsgId()));
        dataEntity.setPushContent(detail.getPushContent());
        dataEntity.setStatus(detail.getStatus());
        Document imgBuf = new Document();
        imgBuf.put("iLen", detail.getImgBuf().getILen());
        dataEntity.setImgBuf(imgBuf);
        dataEntity.setToUserName(detail.getToUserName());
        message.setData(dataEntity);

        if (messageDto.getReply() != null){
            ReplyDto reply = messageDto.getReply();
            Document replyDoc = new Document();
            replyDoc.put("content", reply.getContent());
            replyDoc.put("data", JSONObject.parseObject(JSONObject.toJSONString(reply.getData())));
            message.setReply(replyDoc);
        }
        baseMapper.save(message);
    }

    @Override
    public List<String> getMessages(String id) {
        //返回五分钟内群聊信息
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        if (id.endsWith("@chatroom")){

            return new LambdaQueryChainWrapper<>(this.getBaseMapper(), Message.class)
                    .eq(Message::getFromUser, id)
                    .gte(Message::getCreateTime, fiveMinutesAgo)
                    .list()
                    .stream()
                    .map(Message::getReply)
                    .map(Document::toJson)
                    .toList();
        }
        return new LambdaQueryChainWrapper<>(this.getBaseMapper(), Message.class).eq(Message::getFromUser, id).list().stream().map(Message::getReply).map(Document::toJson).toList();
    }
}
