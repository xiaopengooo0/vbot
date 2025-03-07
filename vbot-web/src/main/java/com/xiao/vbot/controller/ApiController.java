package com.xiao.vbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiaopeng
 * @Description: 
 * @DateTime: 2025/3/4 下午5:31 星期二
 **/
@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    /**
     * 接收微信消息
     * {
     *     "TypeName": "AddMsg",
     *     "Appid": "wx_kJyy8L7b0i5D12eY3DwnN",
     *     "Data": {
     *         "MsgId": 369016472,
     *         "FromUserName": {
     *             "string": "wxid_oc3emvv40ckj21"
     *         },
     *         "ToUserName": {
     *             "string": "wxid_l3ldj618ql3b22"
     *         },
     *         "MsgType": 1,
     *         "Content": {
     *             "string": "你好"
     *         },
     *         "Status": 3,
     *         "ImgStatus": 1,
     *         "ImgBuf": {
     *             "iLen": 0
     *         },
     *         "CreateTime": 1741338156,
     *         "MsgSource": "<msgsource>\n\t<bizflag>0</bizflag>\n\t<pua>1</pua>\n\t<eggIncluded>1</eggIncluded>\n\t<signature>V1_1fkpTST2|v1_1fkpTST2</signature>\n\t<tmp_node>\n\t\t<publisher-id></publisher-id>\n\t</tmp_node>\n</msgsource>\n",
     *         "PushContent": "༼  ་ཅ་༽ : 你好",
     *         "NewMsgId": 8811873591110766073,
     *         "MsgSeq": 835285143
     *     },
     *     "Wxid": "wxid_l3ldj618ql3b22"
     * }
     * @param request
     */
    @PostMapping("/callback")
    public void callback(HttpServletRequest request) {
        //查看参数结构
        request.getParameterMap().forEach((k, v) -> {
            logger.info("请求key{} : {}", k, v);
        });
        //读取request
        try {
            String body = request.getReader().readLine();
            logger.info("请求body{}", body);
        } catch (Exception e) {
            logger.error("读取request异常", e);
        }


    }
}
