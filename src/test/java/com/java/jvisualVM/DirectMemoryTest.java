/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: DirectMemoryTest.java
 * @Package com.java.jvisualVM
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年3月5日 下午10:31:10
 * @version
 */
package com.java.jvisualVM;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;


/**
 * @author liqiuwei
 * @create time:2016年3月5日下午10:31:10
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class DirectMemoryTest {

    public static void main(String[] args) {
        //showOOM3();
        showOOM2();
        //showOOM();
        //	showMemInfo();
    }

    /**
     * VM参数： -XX:+PrintGC  -XX:+PrintGCTimeStamps -verbose:gc -Xloggc:c:/gc.log -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void showMemInfo() {
        Class<?> c;
        try {
            c = Class.forName("java.nio.Bits");
            Field maxMemory = c.getDeclaredField("maxMemory");
            maxMemory.setAccessible(true);
            Field reservedMemory = c.getDeclaredField("reservedMemory");
            reservedMemory.setAccessible(true);
            Long maxMemoryValue = (Long) maxMemory.get(null);
            Long reservedMemoryValue = (Long) reservedMemory.get(null);
            System.out.println("##maxMemoryValue==" + maxMemoryValue + "##reservedMemoryValue=" + reservedMemoryValue);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * VM参数：    -Xms20M -Xmx64M -Xmn10M -XX:+PrintGC   -XX:+PrintGCTimeStamps
     * 发生Full  gc
     * 增加-XX:+DisableExplicitGC
     * 我们设置了JVM堆 64M限制，然后在 直接内存上分配了 100MB空间，程序执行后直接报错：Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory。接着我设置 -Xmx200M，程序正常结束。然后我修改配置： -Xmx64M  -XX:MaxDirectMemorySize=200M，程序正常结束。
     * 因此得出结论： 直接内存DirectMemory的大小默认为 -Xmx 的JVM堆的最大值,但是并不受其限制，而是由JVM参数 MaxDirectMemorySize单独控制
     */
    public static void showOOM() {
        //System.out.println("##"+VM.maxDirectMemory());
        ByteBuffer.allocateDirect(1024 * 1024 * 100); // 100MB
    }

    /**
     * allocateDirect  在直接内存影射
     * VM参数：    -Xms20M -Xmx64M -Xmn10M -XX:+PrintGC   -XX:+PrintGCTimeStamps
     * 发生了多次full gc，原因在于直接内存不受 GC(新生代的Minor GC)影响，只有当执行老年代的 Full GC时候才会顺便回收直接内存！
     * 配置-XX:MaxDirectMemorySize=200M
     * 使得full gc减少。但时间增大
     * 而直接内存是通过存储在JVM堆中的DirectByteBuffer对象来引用的，所以当众多的 DirectByteBuffer对象从新生代被送入老年代后才触发了 full gc。
     */
    public static void showOOM2() {
        for (int i = 0; i < 20000; i++) {

            //ByteBuffer.allocate(1024*100);  //100K
            // System.out.println("i=="+i);
            ByteBuffer.allocateDirect(1024 * 100);  //1M
        }
        System.out.println("##sdds==");
    }

    /**
     * VM参数：    -Xms20M -Xmx64M -Xmn10M -XX:+PrintGC   -XX:+PrintGCTimeStamps
     * .allocate 意味着直接在 JVM堆上分配内存，所以受 新生代的 Minor GC影响
     * 由于直接在 JVM堆上分配内存，所以触发了多次GC，且不会触及  Full GC，因为对象根本没机会进入老年代
     */
    public static void showOOM3() {
        for (int i = 0; i < 20000; i++) {
            ByteBuffer.allocate(1024 * 100);  //100K

        }
    }
}
