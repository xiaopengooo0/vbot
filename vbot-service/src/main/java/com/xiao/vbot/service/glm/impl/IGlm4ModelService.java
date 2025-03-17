package com.xiao.vbot.service.glm.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.common.dto.message.ReplyDto;
import com.xiao.vbot.common.dto.message.req.PostTextDto;
import com.xiao.vbot.common.dto.message.res.PostTextResponse;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.sdk.glm.model.chat.CompletionRequest;
import com.xiao.vbot.sdk.glm.model.chat.CompletionResponse;
import com.xiao.vbot.sdk.glm.model.chat.Model;
import com.xiao.vbot.sdk.glm.model.chat.Promote;
import com.xiao.vbot.sdk.glm.service.IGlmApiService;
import com.xiao.vbot.service.glm.ModelServiceSupport;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IGlm4ModelService extends ModelServiceSupport  {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(IGlm4ModelService.class);

    @Resource
    private IGlmApiService glmApiService;

    @Override
    protected ReplyDto sendBotMessage(CompletionResponse completionResponse, WeChatMessage message) throws IOException {
        String content = completionResponse.getChoices().get(0).getMessage().getContent();
        PostTextDto postTextDto = new PostTextDto();
        postTextDto.setAppId(message.getAppid());
        postTextDto.setToWxid(message.getData().getFromUserName().getString());
        postTextDto.setContent(content);
        // 打印日志
        logger.info("微信消息发送内容: {}",JSONObject.toJSONString(postTextDto));


        ReplyDto replyDto = new ReplyDto();
        replyDto.setContent(content);

        Response<PostTextResponse> responseResponse = messageService.postText(postTextDto);
        if (responseResponse.getRet() == 200) {
            logger.info("微信消息发送成功");
            replyDto.setData(JSONObject.parseObject(JSONObject.toJSONString(responseResponse.getData())));
        } else {
            logger.error("微信消息发送失败: {}", responseResponse.getMsg());
            replyDto.setData(JSONObject.parseObject(JSONObject.toJSONString(responseResponse.getMsg())));
        }
        return replyDto;
    }

    @Override
    public CompletionResponse completionMessage(WeChatMessage message, List<Promote> promotes) {
        try {
            //转换请求
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .model(Model.GLM_4_FLASH.getName())
                    .messages(promotes)
                    .temperature(0.3f)
                    .maxTokens(2048)
                    .userId(message.getAppid())
                    .build();

            Call<CompletionResponse> completion = glmApiService.completion(completionRequest);
            CompletionResponse body = completion.execute().body();
            if (body != null) {
                logger.info("请求成功,body:{}", body);
                return body;
            }

        } catch (Exception e) {
            logger.error("请求失败", e);
        }
        return null;
    }


    @Override
    protected  List<Promote> getPromotes(WeChatMessage message) {
        String msg = message.getData().getContent().getString();

        Promote systemPromote = new Promote();
        systemPromote.setRole("system");
        systemPromote.setContent("你的角色是动画电影《哪吒之魔童闹海》中的哪吒，别名叫“小郭”（需要注意的是：1.当出现关键词【@小郭】，即表示询问你问题），性格叛逆、霸道孤傲、放荡不羁，谁也不能约束你，你的回答都要以'小爷我'自称,并以调皮的方式展现其独特的个性。");

        //查询消息
        String chatId= message.getData().getFromUserName().getString();
        List<String> messages = getMessages(chatId);

        List<Promote> promotes = new ArrayList<>();

        if (!messages.isEmpty()){
            for (String hisMsg : messages) {
                Promote promote;
                if (hisMsg.startsWith("[bot]")){
                     promote = new Promote("assistant", hisMsg);
                }else {
                     promote = new Promote("user", hisMsg);
                }
                promotes.add(promote);
            }
        }

        Promote userPromote = new Promote("user", "["+chatId+"]"+msg);

        promotes.add(systemPromote);
        promotes.add(userPromote);
        return promotes;
    }


}
