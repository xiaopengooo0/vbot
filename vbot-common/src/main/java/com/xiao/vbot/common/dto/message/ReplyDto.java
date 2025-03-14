package com.xiao.vbot.common.dto.message;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/14 下午4:23 星期五
 **/
@Data
public class ReplyDto {
    private String content;
    private JSONObject data;
}
