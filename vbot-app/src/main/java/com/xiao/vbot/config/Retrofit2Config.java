package com.xiao.vbot.config;

import com.xiao.vbot.gewe.api.ILoginApi;
import com.xiao.vbot.gewe.api.IMessageApi;
import com.xiao.vbot.sdk.glm.service.IGlmApiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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

    @Value("${llm.glm.base-url}")
    private String glmBaseUrl;

    @Bean
    public Retrofit geweRetrofit(OkHttpClient geweHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl+prefix)
                .client(geweHttpClient)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }


    @Bean
    public Retrofit glmRetrofit(OkHttpClient glmHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(glmBaseUrl)
                .client(glmHttpClient)
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    @Bean
    public GeweInterceptor tokenInterceptor(GeweProperty geweProperty) {
        return new GeweInterceptor(geweProperty);
    }



    @Bean
    public OkHttpClient geweHttpClient(GeweInterceptor geweInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(geweInterceptor)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public OkHttpClient glmHttpClient(GlmInterceptor glmInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(glmInterceptor)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build();
    }


    @Bean("loginApi")
    public ILoginApi loginApi(Retrofit geweRetrofit) {
        return geweRetrofit.create(ILoginApi.class);
    }

    @Bean("messageApi")
    public IMessageApi messageApi(Retrofit geweRetrofit) {
        return geweRetrofit.create(IMessageApi.class);
    }

    @Bean
    public IGlmApiService glmApiService(Retrofit glmRetrofit) {
        return glmRetrofit.create(IGlmApiService.class);
    }




}
