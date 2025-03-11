package com.xiao.vbot.service.glm;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.WeChatMessage;

public interface IModelService {
    JSONObject processMessage(WeChatMessage message);
}