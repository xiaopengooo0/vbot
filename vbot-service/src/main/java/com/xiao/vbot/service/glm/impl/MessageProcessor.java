package com.xiao.vbot.service.glm.impl;

import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.glm.sdk.model.chat.Model;
import com.xiao.vbot.service.glm.IModelService;
import com.xiao.vbot.service.glm.ModelServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MessageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    @Resource
    private ModelServiceFactory modelServiceFactory;

    public void processMessage(WeChatMessage message) {
        String modelName = determineModelName(message); // 根据消息内容确定模型名称
        IModelService modelService = modelServiceFactory.getModelService(modelName);
        if (modelService != null) {
            modelService.processMessage(message);
        } else {
            // 处理未知模型的情况
            logger.error("未查到相关引用Model服务: {}", modelName);
        }
    }

    private String determineModelName(WeChatMessage message) {

        MessageDetail data = message.getData();
        int msgType = data.getMsgType();
        String targetUser = data.getToUserName().getString();
        String content = data.getContent().getString();
        // 根据消息内容确定模型名称,默认返回GLM_4_FLASH

        return Model.GLM_4_FLASH.getName(); // 示例
    }
}