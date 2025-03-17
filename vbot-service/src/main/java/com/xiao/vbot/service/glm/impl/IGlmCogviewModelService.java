package com.xiao.vbot.service.glm.impl;

import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.service.glm.IModelService;
import org.springframework.stereotype.Service;

@Service
public class IGlmCogviewModelService implements IModelService {
    @Override
    public void processMessage(WeChatMessage message) {
        // 处理图片理解消息
    }
}