package com.xiao.vbot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午2:58 星期二
 **/
@Data
@ConfigurationProperties(prefix = "gewe")
public class GeweProperty {
    /**
     * token
     */
    private String token;
    /**
     * 设备id
     */
    private String appId;
    /**
     * 请求uuid
     */
    private String uuid;


    private String wxid;


    private String tokenUrl = "/tools/getTokenId";
}
