package com.xiao.vbot.glm.sdk.model.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: xiaopeng
 * @Description:
 * @DateTime: 2025/3/11 上午9:38 星期二
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompletionRequest {
    /**
     * 模型名称
     */
    private String model;
    /**
     * 提示输入
     */
    private List<Promote> messages;
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
     * 模型遇到stop指定的字符时会停止生成。目前仅支持单个stop词，格式为["stop_word1"]。
     */
    private List<String> stop;

    /**
     * 模型工具列表，非必输
     */
    private List<ModelTools> tools;

    /**
     * 模型工具选择，默认为auto,制模型选择调用哪个函数的方式，仅在工具类型为function时补充
     * 非必输
     */
    @JsonProperty("tool_choice")
    private String toolChoice;

    /**
     * 用户id，非必输
     */
    @JsonProperty("user_id")
    private String userId;


}
