package com.xiao.vbot.service.file;

/**
 * @Author: xiaopeng
 * @Description: 文件服务接口
 * @DateTime: 2025/3/20 上午11:23 星期四
 **/
public interface IFileService {
    /**
     * 下载文件
     * @param url
     * @param fileName
     */
    void downloadFile(String url, String fileName);


    /**
     * mp3转.slik
     */
    void mp3ToSlik(String mp3Path, String slikPath);
}
