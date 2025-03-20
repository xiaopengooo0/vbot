package com.xiao.vbot.config;


import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xiao.vbot.config.properties.QiniuProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xiaopeng
 * @Description: 七牛云配置
 * @Date: 2025/3/20 20:05
 */

@Configuration
@EnableConfigurationProperties(QiniuProperty.class)
public class QiniuConfig {


    @Bean
    public Auth qiciuAuth(QiniuProperty qiniuProperty) {
        return Auth.create(qiniuProperty.getAccessKey(), qiniuProperty.getSecretKey());
    }

    @Bean
    public String qiniuToken(QiniuProperty qiniuProperty, Auth auth){
        return auth.uploadToken(qiniuProperty.getBucket());
    }

    @Bean
    public UploadManager qiniuUploadManager() {
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration();
        return new UploadManager(cfg);
    }
}
