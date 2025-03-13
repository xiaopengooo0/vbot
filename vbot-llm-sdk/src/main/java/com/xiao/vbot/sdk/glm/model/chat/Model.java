package com.xiao.vbot.sdk.glm.model.chat;

import lombok.Getter;

/**
 * @Author: xiaopeng
 * @Description: TODO
 * @DateTime: 2025/3/10 下午5:39 星期一
 **/
@Getter
public enum Model {
    GLM_4_FLASH("glm-4-flash", "智谱AI官方大模型，支持多轮对话"),
    GLM_4V_FLASH("glm-4v-flash", "图像理解模型"),
    COGVIEW_3_FLASH("cogview-3-flash", "免费图像生成模型"),
    COGVIDEOX_FLASH("cogvideox-flash", "免费视频生成模型");

    private String name;
    private String desc;

    Model(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
