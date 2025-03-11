package com.xiao.vbot.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author: xiaopeng
 * @Description:
 * @DateTime: 2025/3/11 上午10:04 星期二
 **/

public class BearTokenUtils {


    private static final Logger log = LoggerFactory.getLogger(BearTokenUtils.class);

    //过期事件
    private static final long expireMills = 30 * 60 * 1000L;

    public static Cache<String,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(expireMills- (60 * 1000L), TimeUnit.MILLISECONDS).build();


    public static String getToken(String apiKey, String apiSecret){
        String token = cache.getIfPresent(apiKey);
        if(token != null){
            return token;
        }
        // 创建Token
        Algorithm algorithm = Algorithm.HMAC256(apiSecret.getBytes(StandardCharsets.UTF_8));
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("apiKey", apiKey);
        payload.put("exp", System.currentTimeMillis() + expireMills);
        payload.put("timestamp", Calendar.getInstance().getTimeInMillis());
        HashMap<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("sign_type", "SIGN");
        headerClaims.put("alg", "HS256");
        token = JWT.create().withPayload(payload).withHeader(headerClaims).sign(algorithm);
        log.info("token:{}",token);
        cache.put(apiKey,token);
        return token;
    }
}