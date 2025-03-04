package com.xiao.vbot.gewe.dto.login.res;

import com.xiao.vbot.gewe.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午4:23 星期二
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QrResponse extends BaseDto {
    private String uuid;
    private String qrData;
    private String qrImgBase64;
}
