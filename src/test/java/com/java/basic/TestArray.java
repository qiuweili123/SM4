/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: TestArray.java
 * @Package com.java.basic
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月18日 上午10:48:37
 * @version
 */
package com.java.basic;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liqiuwei
 * @create time:2016年11月18日上午10:48:37
 * @Description:打印数组
 */
public class TestArray {

    public static void main(String[] args) {
        String[] strs = new String[]{"test04", "test10", "test03"};
        String str = Arrays.toString(strs);
        System.out.println(str + "==" + str.indexOf("test03"));
        List<String> list = Lists.newArrayList();

        // 数组类型转换
        String[] strIds = new String[]{"1", "2", "3"};
        Long[] longIds = (Long[]) ConvertUtils.convert(strIds, Long.class);
        System.out.println(Arrays.toString(longIds));
        //数类型转换2

        Long[] longIds1 = Arrays.stream(strIds).map(sId -> Long.parseLong(sId)).toArray(Long[]::new);
        System.out.println("longIds1=" + Arrays.toString(longIds1));
        //转换为List
        List<Long> longList = Arrays.stream(strIds).map(sId -> Long.parseLong(sId)).collect(Collectors.toList());
        //List 转换类型数组
        List<String> stringList = Lists.newArrayList(strIds);
        Long[] longIds2 = new Long[stringList.size()];


        Long[] longIds3 = stringList.toArray(longIds);

    }
}
