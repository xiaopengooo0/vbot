package com.xiao.vbot.common.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/7 下午5:18 星期五
 **/

@Getter
public enum MsgType {
    TEXT(1,"文本"),
    IMAGE(3,"图片"),
    VOICE(34,"语音"),
    VIDEO(43,"视频"),
    EMOTICON(47,"emoji表情"),
    PUBLIC_LINK(49,"公众号链接"), //Data.Content.string中的xml msg.appmsg.type=74 为文件

    CARD(42,"名片"),
    FRIEND_ADD(37,"好友请求");
    @Setter
    private int code;
    @Setter
    private String desc;

    MsgType(int i, String desc) {
        this.code = i;
        this.desc = desc;
    }
}


