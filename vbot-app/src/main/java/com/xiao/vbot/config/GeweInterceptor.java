package com.xiao.vbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import okio.Buffer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author: xiaopeng
 * @Description:
 * @DateTime: 2025/3/4 下午2:42 星期二
 **/
@Component
public class GeweInterceptor implements Interceptor {


    private final GeweProperty geweProperty;

    public GeweInterceptor(GeweProperty geweProperty) {
        this.geweProperty = geweProperty;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        //判断请求地址是否包含tokenUrl
        if (originalRequest.url().toString().contains(geweProperty.getTokenUrl())) {
            return chain.proceed(originalRequest);
        }

        // 只处理 POST 请求，并且请求体是 JSON 格式
        if (originalRequest.method().equalsIgnoreCase("POST") && isJson(originalRequest.body())) {
            RequestBody originalBody = originalRequest.body();
            Buffer buffer = new Buffer();
            originalBody.writeTo(buffer);
            String bodyString = buffer.readString(StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> bodyMap = objectMapper.readValue(bodyString, Map.class);
            bodyMap.put("appId", geweProperty.getAppId());

            String newBodyString = objectMapper.writeValueAsString(bodyMap);
            MediaType contentType = originalBody.contentType();
            RequestBody newBody = RequestBody.create(contentType, newBodyString);

            Request requestWithHeader = originalRequest.newBuilder()
                    .header("X-GEWE-TOKEN", geweProperty.getToken())
                    .post(newBody)
                    .build();

            return chain.proceed(requestWithHeader);
        }

        // 对于非 JSON 请求体或非 POST 请求，直接添加头部
        Request requestWithHeader = originalRequest.newBuilder()
                .header("X-GEWE-TOKEN", geweProperty.getToken())
                .build();

        return chain.proceed(requestWithHeader);
    }

    private boolean isJson(RequestBody body) {
        if (body == null) return false;
        MediaType contentType = body.contentType();
        return contentType != null && contentType.type().equals("application") && contentType.subtype().equals("json");
    }
}
