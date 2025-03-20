package com.xiao.vbot;

import com.coze.openapi.client.common.pagination.PageResp;
import com.coze.openapi.client.workspace.ListWorkspaceReq;
import com.coze.openapi.client.workspace.model.Workspace;
import com.coze.openapi.service.service.CozeAPI;
import com.mongoplus.conditions.query.LambdaQueryChainWrapper;
import com.mongoplus.mapper.BaseMapper;
import com.mongoplus.mapping.TypeReference;
import com.xiao.vbot.common.dto.callback.Content;
import com.xiao.vbot.common.dto.callback.MessageDetail;
import com.xiao.vbot.common.dto.callback.UserName;
import com.xiao.vbot.common.dto.callback.WeChatMessage;
import com.xiao.vbot.gewe.entity.Message;
import com.xiao.vbot.service.coze.CozeApiServiceImpl;
import com.xiao.vbot.service.file.IFileService;
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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    @Resource
    private CozeAPI cozeApi;

    @Resource(name = "qiniuFileServiceImpl")
    private IFileService fileService;


    @Resource
    private CozeApiServiceImpl cozeApiService;

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
        LocalDateTime day = LocalDateTime.now().minusDays(5);
//        List<Message> list = new LambdaQueryChainWrapper<>(baseMapper, Message.class)
//                .eq(message -> message.getData().getCreateTime(), fiveMinutesAgo)
//                .list();
//        for (Message message : list) {
//            System.out.println(message);
//        }

        List<Message> messages = baseMapper.queryCommand("{'Data.CreateTime': {$gte: '"+day+"'}}", Message.class);
        for (Message message : messages) {
            System.out.println(message);
        }
    }





    @Test
    public void mongoTest() {

        String time = "1741942448";
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(time), 0, ZoneOffset.UTC);
        System.out.println("UTC 时间: " + localDateTime);

        // 如果需要转换为本地时区时间，可以进一步处理
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("本地时区时间: " + zonedDateTime.toLocalDateTime());

    }



    @Test
    public void cozeApi01() {
        PageResp<Workspace> list = cozeApi.workspaces().list(new ListWorkspaceReq());
        for (Workspace workspace : list.getItems()) {
            System.out.println(workspace);
        }
    }


    @Test
    public void cozeApi02() {
//        String message = "你好";
//        String voice = cozeApiService.getVoice(message);
//        System.out.println(voice);
        String input = "已成功使用指定音色“zh_male_sunwukong_mars_bigtts”将文本“你好”合成为音频，音频链接为：[https://lf-bot-studio-plugin-resource.coze.cn/obj/bot-studio-plugin-plugin-tos/artist/image/f148e7cf008d4368ae3aa517cb77dc67.mp3](https://lf-bot-studio-plugin-resource.coze.cn/obj/bot-studio-plugin-plugin-tos/artist/image/f148e7cf008d4368ae3aa517cb77dc67.mp3)。你可以点击链接播放音频。";
        String urlPattern = "\\[(https?://[^]]+)]";
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String url = matcher.group(1);
            System.out.println("提取的URL: " + url);
        } else {
            System.out.println("未找到URL");
        }

    }


    @Test
    public void qiniuApi03() {
        fileService.uploadFile("C:\\Users\\xiaopeng\\Pictures\\R-C.jpg");
    }

    @Test
    public void qiniuApi04() throws Exception {
        String key = "FpHNvGBPmbVvDpdJK6cApkrn-vmw";
        fileService.downloadFile(key, "C:\\Users\\xiaopeng\\Downloads\\R-C.jpg");
    }

}
