package com.xiao.vbot.sdk.glm.model.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xiaopeng
 * @Description:
 * @DateTime: 2025/3/11 上午9:38 星期二
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promote {
    private String role;
    private String content;
}