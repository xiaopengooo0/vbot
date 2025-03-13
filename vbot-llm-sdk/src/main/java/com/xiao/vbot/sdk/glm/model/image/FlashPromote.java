package com.xiao.vbot.sdk.glm.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: xiaopeng
 * @Description:
 * @DateTime: 2025/3/11 下午2:46 星期二
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashPromote {
    private String role;
    private List<FlashContent> content;
}

abstract class FlashContent { }

@EqualsAndHashCode(callSuper = true)
@Data
class ImageContent extends FlashContent {
    private String type;
    @JsonProperty("image_url")
    private String imageUrl;
    private String text;
}
@EqualsAndHashCode(callSuper = true)
@Data
class VideoContent extends FlashContent {
    private String type;
    @JsonProperty("video_url")
    private String videoUrl;
    private String text;
}
