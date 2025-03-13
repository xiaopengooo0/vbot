package com.xiao.vbot.sdk.glm.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiaopeng
 * @Description: 图像生成
 * @DateTime: 2025/3/11 上午11:52 星期二
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlmCogview3Request {
    private  String model;
    private  String promote;
    private  String size;
    @JsonProperty("user_id")
    private  String userId;
}
