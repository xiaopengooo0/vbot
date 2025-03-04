package com.xiao.vbot.gewe.dto.message.req;

import com.xiao.vbot.gewe.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午5:20 星期二
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PostImageDto extends BaseDto {
    /**
     * 好友/群的ID
     */
    private String toWxid;
    /**
     * 图片链接
     */
    private String imgUrl;

}
