package com.java.jvisualVM;

import java.util.concurrent.TimeUnit;

/**
 * @author liqiuwei
 * @create time:2015年11月12日下午3:24:54
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class YoungGenGC {

    private static final int _1MB = 1024 * 1024;

    public static void main(final String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        //System.out.println("start.11......");
        //testAllocation();
        //testPretenureSizeThreshold();
        //testTenuringThreshold();
        // testTenuringThreshold2();
        // testHandlePromotion();
        testSurvivorRatio();
        TimeUnit.SECONDS.sleep(1);
        //System.out.println("end........");
    }

    /**
     * VM参数：-verbose:gc -Xloggc:c:/gc.log -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 参数说明： -Xmn10 10M分配给新生代
     * -XX:SurvivorRatio=8决定了新生代中eden与survivor的空间比例是8：2
     * <p>
     * 这次GC是发生的原因是为allocation4分配内存的时候，eden已经被占用了6M，剩余空间已不足分配allocation4所需的4M内存，因此发生Minor GC。
     * GC期间虚拟机发现已有的3个2M大小的对象全部无法放入survivor空间（survivor空间只有1M大小），
     * 所以直接转移到老年代去。GC后4M的allocation4对象分配在eden中。
     */
    @SuppressWarnings("unused")
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     * <p>
     * 参数说明：配置了PretenureSizeThreshold的情况下，对象大于设置值将直接在老年代分配。
     * 执行testPretenureSizeThreshold()方法后，我们看到eden空间几乎没有被使用，
     * 而老年代的10M控件被使用了40%，也就是4M的allocation对象直接就分配在老年代中，
     * 则是因为PretenureSizeThreshold被设置为3M，
     * 因此超过3M的对象都会直接从老年代分配。
     */
    @SuppressWarnings("unused")
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];  //直接分配在老年代中
    }

    /**
     * VM参数：-verbose:gc -Xloggc:$CATALINA_HOME/logs/gc.log -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     * -XX:MaxTenuringThreshold:MaxTenuringThreshold设置对象在survivor的停留次数。
     * +PrintTenuringDistribution：输出显示在survivor空间里面有效的对象的岁数情况
     * 添加-XX:+PrintTenuringDistribution 参数观察各个Age的对象总大小，观察后设置-XX:MaxTenuringThreshold=5。
     */
    /**
     * @Description:TODO(这里用一句话描述这个方法的作用)
     * @return: void
     * @author:liqiuwei 2015年11月12日下午3:12:20
     * @update1:updater:liqiuwei updatetime:2015年11月12日下午3:12:20 TODO(描述修改内容)
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     * 执行testTenuringThreshold2()方法，并将设置-XX:MaxTenuringThreshold=15，发现运行结果中survivor占用仍然为0%，而老年代比预期增加了6%，也就是说allocation1、allocation2对象都直接进入了老年代，而没有等待到15岁的临界年龄。因为这2个对象加起来已经到达了512K，并且它们是同年的，
     * 满足同年对象达到survivor空间的一半规则。我们只要注释掉其中一个对象new操作，就会发现另外一个就不会晋升到老年代中去了。
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];   // allocation1+allocation2大于survivo空间一半
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
        System.out.println("");
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
     * <p>
     * 在Minor GC触发时，会检测之前每次晋升到老年代的平均大小是否大于老年代的剩余空间，如果大于，改为直接进行一次Full GC，
     * 如果小于则查看HandlePromotionFailure(jdk7已经作废此参数)设置看看是否允许担保失败，如果允许，那仍然进行Minor GC，如果不允许，则也要改为进行一次Full GC
     */
    @SuppressWarnings("unused")
    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

    /**
     * 1、-Xmx20m -Xms20m -Xmn1m -XX:+UseSerialGC -XX:+PrintGCDetails
     * 结果：没有触发GC，全部分配在老年代。因幸存区不够大。
     * 2、-Xmx20m -Xms20m -Xmn15m -XX:+UseSerialGC -XX:+PrintGCDetails
     * 没有触发GC，全部分配在eden，老年代没有使用
     * 3、-Xmx20m -Xms20m –Xmn7m  -XX:+UseSerialGC -XX:+PrintGCDetails
     * 进行了2次新生代GC，s0、s1 太小，对象直接进入老年代
     * 4、-Xmx20m -Xms20m -Xmn7m   -XX:SurvivorRatio=2  -XX:+UseSerialGC -XX:+PrintGCDetails
     * 进行了3次新生代GC,新生代还是太小
     * 5、-Xmx20m -Xms20m -Xmn10m   -XX:SurvivorRatio=2  -XX:+UseSerialGC -XX:+PrintGCDetails
     * 进行了2次新生代GC，比例分配，新生代 老年代对半开，对象全部留在新生代
     * 6、-Xmx20m -Xms20m -Xmn10m   -XX:SurvivorRatio=4  -XX:+UseSerialGC -XX:+PrintGCDetails
     * s0(2):eden 2:3减少了s0 s1 进行了2次新生代GC,继续缩小s0
     */
    public static void testSurvivorRatio() {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}  