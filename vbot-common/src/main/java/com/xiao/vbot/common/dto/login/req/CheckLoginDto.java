package com.xiao.vbot.common.dto.login.req;

import com.xiao.vbot.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: xiaopeng
 * @Description: 检查登录
 * @DateTime: 2025/3/4 下午4:25 星期二
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckLoginDto extends BaseDto {
    private String uuid;
    private String passTicket;
}
