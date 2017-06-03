package com.java.io;

import org.junit.Test;

import java.io.*;

/**
 * 占用内存少，而且最快的获取海量文件的java代码
 *
 * @author www.zuidaima.com
 **/
public class HugeFileLineCounter {

    public static final String hugeFilePath = "F:/BaiduYunDownload/cn_windows_server_2012_r2_x64_dvd_2707961.iso";


    // samples: 20
    // max: 1353
    // average: 1308.55
    // median: 1309
    @Test
    public void count1() throws IOException {
        long start = System.currentTimeMillis();
        LineNumberReader reader = new LineNumberReader(new FileReader(new File(hugeFilePath)));
        reader.skip(Long.MAX_VALUE);
        reader.close();
        long end = System.currentTimeMillis();
        System.out.println(reader.getLineNumber() + "##time=" + (end - start) / 1000);

    }

    // most quickly sloution
    // samples: 20
    // max: 654
    // average: 507.6
    // median: 494
    @Test
    public void count2() throws IOException {
        long start = System.currentTimeMillis();
        InputStream is = new BufferedInputStream(new FileInputStream(hugeFilePath));
        int count = 0;
        try {
            byte[] c = new byte[1024];
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') ++count;
                }
            }
        } finally {
            is.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(count + "##time=" + (end - start) / 1000);

    }

    // samples: 20
    // max: 622
    // average: 504.8
    // median: 495
    @Test
    public void count3() throws IOException {
        long start = System.currentTimeMillis();
        InputStream is = new BufferedInputStream(new FileInputStream(hugeFilePath));
        int count = 0;
        try {
            byte[] c = new byte[2048];
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') ++count;
                }
            }
        } finally {
            is.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(count + "##time=" + (end - start) / 1000);

    }

    // samples: 20
    // max: 1138
    // average: 1050.75
    // median: 1047
    @Test
    public void count4() throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(hugeFilePath), "UTF8"));
        int count = 0;
        try {
            char[] c = new char[1024];
            int readChars = 0;
            while ((readChars = reader.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') ++count;
                }
            }
        } finally {
            reader.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(count + "##time=" + (end - start) / 1000);
    }
}