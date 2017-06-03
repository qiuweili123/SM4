package com.java.io;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownUtil {
    // 定义下载资源的路径
    private String path;
    // 指定所下载的文件的保存位置
    private String targetFile;
    // 定义需要使用多少线程下载资源
    private int threadNum;
    // 定义下载的线程对象
    private DownThread[] threads;
    // 定义下载的文件的总大小
    private int fileSize;

    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.threadNum = threadNum;
        // 初始化threads数组
        threads = new DownThread[threadNum];
        this.targetFile = targetFile;
    }

    public void download() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, " + "application/x-shockwave-flash, application/xaml+xml, " + "application/vnd.ms-xpsdocument, application/x-ms-xbap, " + "application/x-ms-application, application/vnd.ms-excel, " + "application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        // 得到文件大小
        fileSize = conn.getContentLength();
        conn.disconnect();
        System.out.println("##" + fileSize);
        int currentPartSize = fileSize / threadNum + 1;//这里不必一定要加1,不加1也可以
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");

        // 设置本地文件的大小
        file.setLength(fileSize);
        file.close();

        for (int i = 0; i < threadNum; i++) {
            // 计算每条线程的下载的开始位置
            int startPos = i * currentPartSize;
            // 每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            // 定位该线程的下载位置
            currentPart.seek(startPos);
            // 创建下载线程
            threads[i] = new DownThread(startPos, currentPartSize, currentPart, path);
            // 启动下载线程
            threads[i].start();
        }
    }

    // 获取下载的完成百分比
    public double getCompleteRate() {
        // 统计多条线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }
        // 返回已经完成的百分比
        return sumSize * 1.0 / fileSize;
    }
}
