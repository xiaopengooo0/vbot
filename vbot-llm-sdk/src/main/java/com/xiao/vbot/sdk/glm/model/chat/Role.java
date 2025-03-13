package com.xiao.vbot.sdk.glm.model.chat;

import lombok.Getter;

/**
 * @Author: xiaopeng
 * @Description: 角色
 * @DateTime: 2025/3/10 下午5:45 星期一
 **/
@Getter
public enum Role {
    USER("user"),
    ASSISTANT("assistant"),
    SYSTEM("system");

    private final String role;

    Role(String role) {
        this.role = role;
    }

}