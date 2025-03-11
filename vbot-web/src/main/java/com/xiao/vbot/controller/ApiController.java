package com.xiao.vbot.controller;

import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.service.glm.impl.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private MessageProcessor messageProcessor;

    @PostMapping("/callback")
    public void callback(@RequestBody WeChatMessage message, HttpServletRequest request) {
        try {
            String body = request.getReader().readLine();
            logger.info("请求body{}", body);
            messageProcessor.processMessage(message);
        } catch (Exception e) {
            logger.error("读取request异常", e);
        }
    }
}