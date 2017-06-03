/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestGeneric.java
 * @Package com.java.generic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lenovo
 * @date: 2016年7月26日 下午4:23:16
 * @version
 */
package com.java.generic;

/**
 * @author lenovo
 * @create time:2016年7月26日下午4:23:16
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class TestGeneric {
    public static void main(String[] args) {
        /*
         * B<String> a = new B<String>(); a.doGetClass();
		 */
        // 通过此种方式
        C<B> c = new C<>();
        c.save(new B());

    }
}
