package com.xiao.vbot.service.core;

import com.xiao.vbot.common.dto.message.req.PostImageDto;
import com.xiao.vbot.common.dto.message.req.PostTextDto;
import com.xiao.vbot.common.dto.message.res.PostImageResponse;
import com.xiao.vbot.common.dto.message.res.PostTextResponse;
import com.xiao.vbot.common.res.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: 消息处理接口
 * @DateTime: 2025/3/10 下午4:24 星期一
 **/
public interface IMessageService {


    /**
     * 发送文本消息
     * @param messageDto
     * @return
     */

    Response<PostTextResponse> postText(PostTextDto messageDto) throws IOException;


    /**
     * 发送图片消息
     * @param messageDto
     * @return
     */
    Response<PostImageResponse> postImage(PostImageDto messageDto) throws IOException;

}
