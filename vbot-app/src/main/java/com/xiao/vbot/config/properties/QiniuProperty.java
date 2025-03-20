package com.xiao.vbot.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xiaopeng
 * @Description:
 * @Date: 2025/3/20 20:07
 */

@ConfigurationProperties(prefix = "qiniu")
@Data
public class QiniuProperty {
    private String accessKey;
    private String secretKey;
    private String bucket;
}
