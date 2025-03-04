package com.xiao.vbot.gewe.api;

import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.gewe.dto.message.req.PostImageDto;
import com.xiao.vbot.gewe.dto.message.req.PostTextDto;
import com.xiao.vbot.gewe.dto.message.res.PostTextResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author: xiaopeng
 * @Description: 消息接口
 * @DateTime: 2025/3/4 下午4:57 星期二
 **/
public interface IMessageApi {

    /**
     * 发送文本消息
     * @param messageDto
     * @return
     */
    @POST("/message/postText")
    Call<Response<PostTextResponse>> postText(@Body PostTextDto messageDto);


    @POST("/message/postImage")
    Call<Response> postImage(@Body PostImageDto messageDto);
}
