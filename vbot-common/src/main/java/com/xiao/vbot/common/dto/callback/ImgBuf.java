package com.xiao.vbot.common.dto.callback;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImgBuf {
    @JsonProperty("iLen")
    private int iLen;
}
