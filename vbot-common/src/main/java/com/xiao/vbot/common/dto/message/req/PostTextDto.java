package com.xiao.vbot.common.dto.message.req;

import com.xiao.vbot.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午5:05 星期二
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PostTextDto extends BaseDto {
    /**
     * 接收消息的微信号
     */
    private String toWxid;
    /**
     * 消息内容
     */
    private String content;
    /**
     * @的好友，多个英文逗号分隔。群主或管理员@全部的人，则填写'notify@all'
     */
    private String ats;
}
