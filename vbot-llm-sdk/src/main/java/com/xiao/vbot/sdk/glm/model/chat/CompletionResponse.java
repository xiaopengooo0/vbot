package com.xiao.vbot.sdk.glm.model.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/11 下午4:02 星期二
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompletionResponse {
    private String id;
    private String created;
    private String model;
    private List<ChoiceResponse> choices;
    private String request_id;
    //忽略解析
    private Usage usage;
}

