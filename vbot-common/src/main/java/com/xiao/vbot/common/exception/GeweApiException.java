package com.xiao.vbot.common.exception;

import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/4 下午4:45 星期二
 **/
public class GeweApiException extends RuntimeException {

    private String message;

    public GeweApiException(IOException e) {
        super(e);
    }

    public GeweApiException(String message) {
        super(message);
        this.message = message;
    }


}
