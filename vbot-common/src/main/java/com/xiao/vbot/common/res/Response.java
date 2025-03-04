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
}
