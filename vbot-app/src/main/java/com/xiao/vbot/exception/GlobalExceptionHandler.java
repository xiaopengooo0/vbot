package com.xiao.vbot.exception;

import com.xiao.vbot.common.exception.GeweApiException;
import com.xiao.vbot.common.res.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: xiaopeng
 * @Description:  统一异常处理
 * @DateTime: 2025/3/5 下午4:55 星期三
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String DEFAULT_ERROR_MESSAGE = "服务器开小差了，请稍后再试";
    private static final int DEFAULT_ERROR_CODE = 500;
    private static final int DEFAULT_ERROR_CODE_404 = 404;
    private static final int DEFAULT_ERROR_CODE_403 = 403;



    @ExceptionHandler(GeweApiException.class)
    public Response<String> handleGeweApiException(GeweApiException e) {
        if (e.getMessage() == null) {
            return Response.fail(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
        }
        return Response.fail(DEFAULT_ERROR_CODE, e.getMessage());
    }
}
