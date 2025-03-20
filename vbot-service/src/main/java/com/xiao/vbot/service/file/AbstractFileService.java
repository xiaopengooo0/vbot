package com.xiao.vbot.service.file;

import okhttp3.OkHttpClient;

import javax.annotation.Resource;

/**
 * @Author: xiaopeng
 * @Description:
 * @Date: 2025/3/20 20:28
 */

public abstract class AbstractFileService implements IFileService{


    @Resource(name = "okHttpClient")
    private OkHttpClient okHttpClient;

    @Override
    public void mp3ToSlik(String mp3Path, String slikPath) {

    }

    @Override
    public void mp3ToSlik(String mp3Path, String slikPath, String slikName) {

    }

    protected OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
