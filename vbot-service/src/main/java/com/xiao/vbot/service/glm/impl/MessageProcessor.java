package com.xiao.vbot.service.glm.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.common.dto.message.MessageDto;
import com.xiao.vbot.common.dto.message.req.PostTextDto;
import com.xiao.vbot.common.dto.message.res.PostTextResponse;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.sdk.glm.model.chat.Model;
import com.xiao.vbot.service.core.IMessageRepository;
import com.xiao.vbot.service.core.IMessageService;
import com.xiao.vbot.service.glm.IModelService;
import com.xiao.vbot.service.glm.ModelServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;


@Service
public class MessageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);


    @Resource
    private IMessageRepository messageRepository;

    @Resource
    private ModelServiceFactory modelServiceFactory;



    public void processMessage(WeChatMessage message) throws IOException {
        String modelName = determineModelName(message); // 根据消息内容确定模型名称
        logger.info("确定模型名称: {}", JSONObject.toJSONString(message));
        IModelService modelService = modelServiceFactory.getModelService(modelName);
        if (modelService != null) {
            modelService.processMessage(message);
        } else {
            // 处理未知模型的情况
            logger.error("未查到相关引用Model服务或不需回复: {}", modelName);
            MessageDto messageDto = new MessageDto();
            messageDto.setMessage(message);
            messageRepository.save(messageDto);
        }
    }

    private String determineModelName(WeChatMessage message) {

        MessageDetail data = message.getData();
        int msgType = data.getMsgType();
        String targetUser = data.getToUserName().getString();
        String content = data.getContent().getString();
        String fromUser = data.getFromUserName().getString();
        // 根据消息内容确定模型名称,默认返回GLM_4_FLASH
        if (fromUser.equals("wxid_l3ldj618ql3b22")){
            return null;
        }
        //群聊信息
//        if(fromUser.endsWith("@chatroom")&&content.contains("@小郭")){
//            return Model.GLM_4_FLASH.getName();
//        }
//
//        if (!fromUser.endsWith("@chatroom")) {
//            return Model.GLM_4_FLASH.getName();
//        }


        return Model.GLM_4_FLASH.getName(); // 示例
    }
}