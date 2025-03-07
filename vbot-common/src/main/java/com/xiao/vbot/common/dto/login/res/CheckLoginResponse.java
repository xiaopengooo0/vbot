package com.xiao.vbot.common.dto.login.res;

import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午4:26 星期二
 **/
@Data
public class CheckLoginResponse {
    private String uuid;
    private String headImgUrl;
    private String nickName;
    private String expiredTime;
    private String status;
    private String loginInfo;
}
