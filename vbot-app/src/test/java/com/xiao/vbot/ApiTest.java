package com.xiao.vbot;

import com.xiao.vbot.common.dto.callback.Content;
import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.UserName;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.service.glm.impl.IGlm4ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/11 下午3:52 星期二
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IGlm4ModelService modelService;

    @Test
    public void glmTest() {

        WeChatMessage weChatMessage = new WeChatMessage();
        MessageDetail messageDetail = new MessageDetail();
        Content content = new Content();
        content.setString("你好");
        messageDetail.setContent(content);
        UserName toUserName = new UserName();
        toUserName.setString("12312315");
        messageDetail.setToUserName(toUserName);
        weChatMessage.setData(messageDetail);
        modelService.processMessage(weChatMessage);


    }
}
