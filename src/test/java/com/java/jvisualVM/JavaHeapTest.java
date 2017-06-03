package com.java.jvisualVM;

import java.util.concurrent.TimeUnit;

/**
 * @author liqiuwei
 * @create time:2016年4月12日上午10:28:30
 * @Description:-verbose:gc -Xms20M -Xmx20M -Xmn1M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class JavaHeapTest {
    public final static int OUTOFMEMORY = 300000000;
    StringBuffer tempOOM = new StringBuffer();
    private String oom;
    private int length;

    public JavaHeapTest(int leng) throws Exception {
        this.length = leng;

        int i = 0;
        while (i < leng) {
            i++;
            try {
                tempOOM.append("a");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                break;
            }
        }
        this.oom = tempOOM.toString();
        

        /*    int size = (int)(1024 * 1024 * m);//想堆内存申请大小
     byte[] buffer = new byte[size];
    Thread.sleep(10000);//线程休眠10秒
    */
    }

    public static void main(String[] args) throws Exception {
        System.out.println("首先休眠10s");
        TimeUnit.SECONDS.sleep(1);
        JavaHeapTest javaHeapTest = new JavaHeapTest(OUTOFMEMORY);
        System.out.println("结束休眠10s");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("结束");
    }

    public String getOom() {
        return oom;
    }

    public int getLength() {
        return length;
    }

}