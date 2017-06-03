package com.java.fetchUrl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1. 采用jsoup解析html
 * 2. 采用了多线程抓取url，提高速度
 * 3. 输入url列表文件一行一个url
 * 4. 输出url和title，keywords，description文件，各字段以\t分隔，已去除title，keywords，description字段中可能存在的\r和\n，以避免其影响逐行读取抓取的url信息
 */


public class GetTitleRunnable implements Runnable {
    private static BufferedReader br;
    private static BufferedWriter bw;

    public GetTitleRunnable() {
    }

    public GetTitleRunnable(String in, String out) {
        if (br == null || bw == null) {
            try {
                br = new BufferedReader(new FileReader(in));
                bw = new BufferedWriter(new FileWriter(out));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close() {
        try {
            if (br != null) br.close();
            if (bw != null) bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTitle(String url) {
        Document doc;

        try {
            //new URL(url);
            Connection con = Jsoup.connect(url);
            con.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
            doc = con.get();
        } catch (IOException e) {
            System.out.println(url);
            return null;
        }

        Elements heads = doc.getElementsByTag("head");

        StringBuilder sb = new StringBuilder();

        for (Element head : heads) {
            Elements titles = head.getElementsByTag("title");
            for (Element title : titles) {
                sb.append(title.text());
            }

            sb.append("\t");
            Elements keys = head.getElementsByAttributeValue("name", "keywords");
            for (Element key : keys) {
                sb.append(key.attr("content"));
            }

            sb.append("\n");
            Elements descs = head.getElementsByAttributeValue("name", "description");
            for (Element desc : descs) {
                sb.append(desc.attr("content"));
            }
        }

        return sb.length() > 3 ? sb.toString().replaceAll("[\r\n]", "") : null;
    }

    public void run() {
        while (true) {
            try {
                String url = null;
                synchronized (br) {
                    url = br.readLine();
                    System.out.println("#url#" + url);
                    if (url == null) break;
                }
                String title = getTitle(url);
                System.out.println("##" + title);
                if (title != null) synchronized (bw) {
                    bw.write(url + "\t" + title + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void MultiThreadsGetTitle(int threadsNum) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadsNum; i++) {
            executor.execute(new GetTitleRunnable());
            System.out.println("thread " + i + " started");
        }
        executor.shutdown();
        System.out.println("shout down");
        boolean isFinish = executor.awaitTermination(5, TimeUnit.DAYS);
        System.out.println("##isFinish=" + isFinish);
        GetTitleRunnable.close();
        System.out.println("shout down close");
    }


}