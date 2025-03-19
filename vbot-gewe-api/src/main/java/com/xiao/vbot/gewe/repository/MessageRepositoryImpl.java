package com.xiao.vbot.gewe.repository;

import com.alibaba.fastjson.JSONObject;
import com.mongoplus.conditions.query.LambdaQueryChainWrapper;
import com.mongoplus.mapper.MongoMapperImpl;
import com.mongoplus.model.PageResult;
import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.common.dto.message.MessageDto;
import com.xiao.vbot.common.dto.message.ReplyDto;
import com.xiao.vbot.gewe.entity.Message;
import com.xiao.vbot.gewe.mapper.MessageMapper;
import com.xiao.vbot.service.core.IMessageRepository;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaopeng
 * @Description: 消息
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
        dataEntity.setCreateTime(detail.getCreateTime());
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
    public List<String> getMessages(String fromUserName) {
        //返回最近二十条信息 content消息+reply消息
        List<Message> messages = baseMapper.queryCommand("{'Data.FromUserName':{$eq:'" + fromUserName + "'}}.sort({'Data.CreateTime': -1}).limit(20)", Message.class);
        List<Message> list = messages.stream().filter(message -> message.getReply() != null).toList();
        if (list.isEmpty()){
            return List.of();
        }
        // 组装消息
        ArrayList<String> messageArr = new ArrayList<>();
        for (Message message : messages) {
            String msg = message.getData().getContentEntity().getString();
            String user = message.getData().getFromUserName().getString();
            if (message.getReply() != null){
                messageArr.add("["+user +"]" + msg);
                String reply  = message.getReply().getString("content");
                messageArr.add("[bot]"+ reply);
            }

        }
        return messageArr;
    }
}
