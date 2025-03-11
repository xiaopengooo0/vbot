package com.xiao.vbot.glm.sdk.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiao.vbot.glm.sdk.model.chat.ModelTools;
import com.xiao.vbot.glm.sdk.model.chat.Promote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: xiaopeng
 * @Description: 图片理解
 * @DateTime: 2025/3/11 下午2:40 星期二
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Glm4VRequest {
    /**
     * 模型名称
     */
    private String model;
    /**
     * 提示输入
     */
    private List<FlashPromote> messages;
    /**
     * 请求id，非必输
     */
    @JsonProperty("request_id")
    private String requestId;
    /**
     * 是否流式输出，默认为false
     */
    private Boolean stream;

    /**
     * 采样温度，控制输出的随机性，默认为0.95
     */
    private Float temperature;

    /**
     * 用温度取样的另一种方法，默认为0.7
     */
    @JsonProperty("top_p")
    private Integer topP;
    /**
     * 最大输出长度，最大输出为4095，默认值为1024
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;



    /**
     * 用户id，非必输
     */
    @JsonProperty("user_id")
    private String userId;
}
