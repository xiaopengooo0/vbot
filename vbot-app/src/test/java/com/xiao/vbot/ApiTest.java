package com.xiao.vbot;

import com.mongoplus.conditions.query.LambdaQueryChainWrapper;
import com.mongoplus.mapper.BaseMapper;
import com.mongoplus.mapping.TypeReference;
import com.xiao.vbot.common.dto.callback.Content;
import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.UserName;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.gewe.entity.Message;
import com.xiao.vbot.service.glm.impl.IGlm4ModelService;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @Resource
    private BaseMapper baseMapper;

    @Test
    public void glmTest() throws IOException {

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


    @Test
    public void mongoTest2() throws SQLException {
        List<Message> list = baseMapper.list(Message.class);
        for (Message message : list) {
            System.out.println(message);
        }
    }

    @Test
    public void messagesTest() {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        List<Message> list = new LambdaQueryChainWrapper<>(baseMapper, Message.class)
                .gte((message -> message.getData().getCreateTime()), fiveMinutesAgo)
                .list();
        for (Message message : list) {
            System.out.println(message);
        }
    }
}
