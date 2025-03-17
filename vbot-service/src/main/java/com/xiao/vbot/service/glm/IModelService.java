package com.xiao.vbot.service.glm;

import com.xiao.vbot.common.dto.callback.WeChatMessage;

import java.io.IOException;

public interface IModelService {
    void processMessage(WeChatMessage message) throws IOException;
}