package com.xiao.vbot.gewe.impl;

import com.xiao.vbot.common.dto.message.req.PostImageDto;
import com.xiao.vbot.common.dto.message.req.PostTextDto;
import com.xiao.vbot.common.dto.message.res.PostImageResponse;
import com.xiao.vbot.common.dto.message.res.PostTextResponse;
import com.xiao.vbot.common.res.Response;
import com.xiao.vbot.gewe.api.IMessageApi;
import com.xiao.vbot.service.core.IMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/11 下午4:35 星期二
 **/
@Service
public class MessageImpl implements IMessageService {


    @Resource
    private IMessageApi messageApi;

    @Override
    public Response<PostTextResponse> postText(PostTextDto messageDto) throws IOException {
        return messageApi.postText(messageDto).execute().body();
    }

    @Override
    public Response<PostImageResponse> postImage(PostImageDto messageDto) throws IOException {
        return messageApi.postImage(messageDto).execute().body();
    }
}
