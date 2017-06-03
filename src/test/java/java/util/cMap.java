package java.util;

/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: cMap.java
 * @Package
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: ard-liqiuwei
 * @date: 2017年2月7日 上午9:33:25
 * @version
 */

/**
 * @author ard-liqiuwei
 * @create time:2017年2月7日上午9:33:25
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class cMap extends HashMap<String, String> {
    public static void main(String[] args) {
        new cMap();
    }

    @Override
    void init() {
        System.out.println("cmap");
    }
}
