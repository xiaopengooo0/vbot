package com.xiao.vbot.service.glm;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.common.dto.message.MessageDto;
import com.xiao.vbot.common.dto.message.ReplyDto;
import com.xiao.vbot.sdk.glm.model.chat.CompletionResponse;
import com.xiao.vbot.sdk.glm.model.chat.Promote;
import com.xiao.vbot.service.core.IMessageRepository;
import com.xiao.vbot.service.core.IMessageService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author: xiaopeng
 * @Description: 服务编排
 * @DateTime: 2025/3/14 下午2:05 星期五
 **/
public abstract class ModelServiceSupport implements IModelService{

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ModelServiceSupport.class);


    @Resource
    protected IMessageRepository messageRepository;

    @Resource
    protected IMessageService messageService;


    @Override
    public void processMessage(WeChatMessage message) throws IOException {

        // 处理消息前
//        获取提示词,上下文信息
        List<Promote> promotes  = getPromotes(message);
        // 处理消息
//       1.请求llmapi
        CompletionResponse completionResponse = completionMessage(message, promotes);

//        2.发送消息到微信
        ReplyDto response = sendBotMessage(completionResponse,message);

        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(message);
        messageDto.setReply(response);

        // 处理消息后，组装请求和响应信息
        messageRepository.save(messageDto);

        JSONObject.parseObject(JSONObject.toJSONString(messageDto));
    }

    protected abstract ReplyDto sendBotMessage(CompletionResponse completionResponse, WeChatMessage message) throws IOException;

    protected abstract CompletionResponse completionMessage(WeChatMessage message, List<Promote> promotes);

    protected abstract List<Promote> getPromotes(WeChatMessage message);



    protected List<String> getMessages(String id){
        return messageRepository.getMessages(id);
    }




}
