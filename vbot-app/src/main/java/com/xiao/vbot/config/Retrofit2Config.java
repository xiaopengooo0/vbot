package com.xiao.vbot.config;

import com.xiao.vbot.gewe.api.ILoginApi;
import com.xiao.vbot.gewe.api.IMessageApi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午2:03 星期二
 **/
@Configuration
@EnableConfigurationProperties(GeweProperty.class)
public class Retrofit2Config {

    @Value("${gewe.base-url}")
    private String baseUrl;

    @Value("${gewe.prefix}")
    private String prefix;

    @Bean
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl+prefix)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    @Bean
    public GeweInterceptor tokenInterceptor(GeweProperty geweProperty) {
        return new GeweInterceptor(geweProperty);
    }



    @Bean
    public OkHttpClient okHttpClient(GeweInterceptor geweInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(geweInterceptor)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build();
    }


    @Bean("loginApi")
    public ILoginApi loginApi(Retrofit retrofit) {
        return retrofit.create(ILoginApi.class);
    }

    @Bean("messageApi")
    public IMessageApi messageApi(Retrofit retrofit) {
        return retrofit.create(IMessageApi.class);
    }


}
