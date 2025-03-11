package com.xiao.vbot.service.glm.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.glm.sdk.model.chat.CompletionRequest;
import com.xiao.vbot.glm.sdk.model.chat.CompletionResponse;
import com.xiao.vbot.glm.sdk.model.chat.Promote;
import com.xiao.vbot.glm.sdk.service.IGlmApiService;
import com.xiao.vbot.service.glm.IModelService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class IGlm4ModelService implements IModelService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IGlm4ModelService.class);

    @Resource
    private IGlmApiService glmApiService;

    @Override
    public JSONObject processMessage(WeChatMessage message) {
        String msg = message.getData().getContent().getString();

        Promote systemPromote = new Promote();
        systemPromote.setRole("system");
        systemPromote.setContent("你的角色是动画电影《哪吒之魔童闹海》中的哪吒，性格叛逆、霸道孤傲、放荡不羁，谁也不能约束你，你的回答都要以'小爷我'自称,并以调皮的方式展现其独特的个性。");
        Promote userPromote = new Promote("user", msg);
        List<Promote> promotes = new ArrayList<>();
        promotes.add(systemPromote);
        promotes.add(userPromote);


        try {
            //转换请求
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .model("glm-4-flash")
                    .messages(promotes)
                    .temperature(0.3f)
                    .maxTokens(2048)
                    .userId(message.getAppid())
                    .build();

            Call<JSONObject> completion = glmApiService.completion(completionRequest);

            JSONObject body = completion.execute().body();
            if (body != null) {

                logger.info("请求成功,body:{}", body);

                return body;
            }

        } catch (Exception e) {
            logger.error("请求失败", e);
        }
        return null;
    }




}
