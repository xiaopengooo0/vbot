package com.xiao.vbot.sdk.glm.service;

import com.alibaba.fastjson.JSONObject;
import com.xiao.vbot.sdk.glm.model.chat.CompletionRequest;
import com.xiao.vbot.sdk.glm.model.image.GlmCogview3Request;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author: xiaopeng
 * @Description: Glm请求接口
 * @DateTime: 2025/3/11 下午2:33 星期二
 **/
public interface IGlmApiService {

    /**
     * 生成图片
     * @param request
     * @return
     */
    @POST("images/generations")
    Call<JSONObject> generateImage(@Body GlmCogview3Request request);


    /**
     * 聊天、图片理解
     * @param completionRequest
     * @return
     */
    @POST("chat/completions")
    Call<JSONObject> completion(@Body CompletionRequest completionRequest);

}
