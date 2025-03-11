package com.xiao.vbot.config;

import com.xiao.vbot.common.utils.BearTokenUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: GlmInterceptor 添加请求头信息
 * @DateTime: 2025/3/11 上午9:57 星期二
 **/
@Component
public class GlmInterceptor implements Interceptor {

    @Value("${llm.glm.token}")
    private String token;

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if(token.isEmpty()){
            throw new RuntimeException("apiKey is empty");
        }
        String[] split = token.split("\\.");
        String key = split[0];
        String secret = split[1];

        //添加Authorization头信息
        Request request = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer "+BearTokenUtils.getToken(key, secret))
                .addHeader("Content-Type", "application/json")
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(request);
    }
}
