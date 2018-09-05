package com.java.jvisualVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池测试
 * jdk1,6常量池放在方法区，jdk1.7常量池放在堆内存，jdk1.8放在元空间里面
 * -Xmx10m -Xms10m -XX:-UseGCOverheadLimit
 * 这里的-XX:-UseGCOverheadLimit是关闭GC占用时间过长时会报的异常，然后限制堆的大小
 * <p>
 * jdk 1.6 1.7
 * -XX:PermSize=10M -XX:MaxPermSize=10m
 * jdk 1.8
 * -XX:MaxMetaspaceSize=10M   -XX:MetaspaceSize=10M
 * <p>
 * jdk1,6常量池放在方法区，jdk1.7常量池放在堆内存，jdk1.8放在元空间里面，
 */

public class ConstantsPoolTest {
    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
