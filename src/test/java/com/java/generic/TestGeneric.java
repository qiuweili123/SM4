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

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

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
        Map map = Maps.newHashMap();
        User user = new User();
        // 通过此种方式
        C<Map> c = new C<>();
        c.save(new HashMap());
        Generic generic = new Generic();

        System.out.println("##sss"+c.getEntity());

        generic.showInfo(map, user);

        E e=new E(map);
        System.out.println("##"+e.getEntity());
    }
}
