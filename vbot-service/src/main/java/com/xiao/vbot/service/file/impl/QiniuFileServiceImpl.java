package com.xiao.vbot.service.file.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.xiao.vbot.service.file.AbstractFileService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.*;

/**
 * @Author: xiaopeng
 * @Description:
 * @Date: 2025/3/20 20:28
 */

@org.springframework.stereotype.Service
public class QiniuFileServiceImpl extends AbstractFileService {

    private static final Logger logger = LoggerFactory.getLogger(QiniuFileServiceImpl.class);

    @Resource
    private UploadManager uploadManager;

    @Resource
    private Auth auth;


    @Value("${qiniu.domain}")
    private String domain;

    @Resource
    private String qiniuToken;

    @Override
    public void downloadFile(String key, String fileName) throws Exception {
        String url = getDownloadUrl(key);

        okhttp3.Response resp = getOkHttpClient().newCall(new Request.Builder().url(url).build()).execute();
        if (resp.isSuccessful()) {
            logger.info("下载成功");
            ResponseBody body = resp.body();
            InputStream is = body.byteStream();
            byte[] data = readInputStream(is);
            //判断文件夹是否存在，不存在则创建
            File file = new File(fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(data);
            fops.close();
        } else {
            logger.error("下载失败");
        }

    }


    private String getDownloadUrl(String key) throws QiniuException {
        DownloadUrl url = new DownloadUrl(domain, false, key);
        return url.buildURL(auth,System.currentTimeMillis()/1000+3600);
    }

    @Override
    public void uploadFile(String filePath) {

        try {
            Response response = uploadManager.put(filePath, null, qiniuToken);
            if (response.isOK()) {
                DefaultPutRet ret = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                logger.info("上传成功,key:{},hash:{}", ret.key, ret.hash);
            } else {
                logger.error("上传失败,{}", response.bodyString());
            }
        } catch (Exception e) {
            logger.error("上传失败{}", e.getMessage());
        }

    }


    /**
     * 读取字节输入流内容
     *
     * @param is
     * @return
     */
    private static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024 * 2];
        int len = 0;
        try {
            while ((len = is.read(buff)) != -1) {
                writer.write(buff, 0, len);
            }
            is.close();
        } catch (IOException e) {
            logger.error("读取字节输入流内容异常", e);
        }
        return writer.toByteArray();
    }
}
