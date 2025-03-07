package com.xiao.vbot.common.dto.login.req;

import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午4:17 星期二
 **/
@Data
public class CallbackDto {
    private String token;
    private String callbackUrl;
}
