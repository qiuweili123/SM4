package com.java.io;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

class DownThread extends Thread {
    // 定义已经该线程已下载的字节数
    public int length;
    // 当前线程的下载位置
    private int startPos;
    // 定义当前线程负责下载的文件大小
    private int currentPartSize;
    // 当前线程需要下载的文件块
    private RandomAccessFile currentPart;
    // 下载路径
    private String path;

    public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart, String path) {
        this.startPos = startPos;
        this.currentPartSize = currentPartSize;
        this.currentPart = currentPart;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, " + "application/x-shockwave-flash, application/xaml+xml, " + "application/vnd.ms-xpsdocument, application/x-ms-xbap, " + "application/x-ms-application, application/vnd.ms-excel, " + "application/vnd.ms-powerpoint, application/msword, */*");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            conn.setRequestProperty("Charset", "UTF-8");
            InputStream inStream = conn.getInputStream();
            // 跳过startPos个字节，表明该线程只下载自己负责哪部分文件。
            inStream.skip(this.startPos);
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            // 读取网络数据，并写入本地文件
            while (length < currentPartSize && (hasRead = inStream.read(buffer)) != -1) {
                currentPart.write(buffer, 0, hasRead);
                // 累计该线程下载的总大小
                length += hasRead;
            }
            currentPart.close();
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
