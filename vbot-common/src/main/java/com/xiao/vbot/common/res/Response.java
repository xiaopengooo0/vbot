package com.xiao.vbot.common.res;

import lombok.Data;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午4:07 星期二
 **/
@Data
public class Response<T> {
    private int ret;
    private String msg;
    private T data;


    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setRet(0);
        response.setData(data);
        return response;
    }


    public static <T> Response<T> fail(int ret, String msg) {
        Response<T> response = new Response<>();
        response.setRet(ret);
        response.setMsg(msg);
        return response;
    }
}
