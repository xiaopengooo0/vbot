package com.xiao.vbot.sdk.glm.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description:
 * @DateTime: 2025/3/11 下午4:02 星期二
 **/
@Data
public class ChoiceResponse {
    private int index;
    private Promote message;
    @JsonProperty("finish_reason")
    private String finishReason;
}
