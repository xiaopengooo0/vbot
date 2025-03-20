package com.xiao.vbot.common.utils;

import java.io.IOException;

/**
 * @Author: xiaopeng
 * @Description: 文件工具类
 * @DateTime: 2025/3/20 上午11:13 星期四
 **/
public class FileUtils {


    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    /**
     *
     * 下载文件
     */
    public static void downloadFile(String url, String fileName) {
        // 创建一个URL对象
        java.net.URL urlObj = null;
        try {
            urlObj = new java.net.URL(url);
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }

        // 创建一个HttpURLConnection对象
        java.net.HttpURLConnection conn = null;
        try {
            conn = (java.net.HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                java.io.InputStream inputStream = conn.getInputStream();
                java.io.FileOutputStream outputStream = new java.io.FileOutputStream(fileName);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
