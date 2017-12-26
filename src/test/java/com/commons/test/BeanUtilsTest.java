package com.commons.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.hibernate.dao.support.BeanUtils;
import org.apache.commons.beanutils.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by lenovo on 2017/8/25.
 */
public class BeanUtilsTest {
    static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public static void main(String[] args) throws Exception {
        testLazyDynaBean();
        //  testDynaBean();
    }

    /**
     * PropertyUtils类 和  BeanUtils类有着同样的作用只不过 PropertyUtils 不进行类型的转换.
     * BeanUtils设置值转换的都是String类型
     *
     * @throws Exception
     */
    public static void testdescribe() throws Exception {
        User user = new User();
        BeanUtils.setProperty(user, "name", "张三");
        BeanUtils.setProperty(user, "age", 12);
        BeanUtils.setProperty(user, "birthday.year", "2014");
        //将JavaBean的各种属性以及值以Map的形式描述
        Map<String, String> describe = BeanUtils.describe(user);

        System.out.println(gson.toJson(describe));
    }

    public static void testDynaBean() throws Exception {
        DynaProperty[] properties = new DynaProperty[]{
                new DynaProperty("albumId", String.class),
                new DynaProperty("name", String.class),
                new DynaProperty("description", String.class),
                new DynaProperty("topPhotoId", String.class),
                new DynaProperty("url", String.class),
                new DynaProperty("enableFor", String.class),
                new DynaProperty("createTime", Date.class),
                new DynaProperty("modifyTime", Date.class)};
        DynaBean dynaBean = createDynaBean(properties, "album");
        Map<String, String> describe = BeanUtils.describe(dynaBean);

        // System.out.println(gson.toJson(dynaBean));
        // 使用PropertyUtils工具类拷贝Bean属性
        // PropertyUtils.copyProperties(dynaBean, infoDTO);
    }

    public static DynaBean createDynaBean(DynaProperty[] properties,
                                          String className) throws Exception {
        // 使用动态Bean的属性定义类
        DynaClass beanClass = new BasicDynaClass(className, null, properties);
        // 根据类，创建一个新的实例
        DynaBean bean = beanClass.newInstance();
        return bean;
    }

    /**
     * 它实现一个动态的Bean，可以直接往里面加入属性，作为一个JavaBean一样使用，也可以用上面的BeanUtils或get/set方法进行操作，而不用事先定义一个标准的JavaBean类.
     */
    public static void testLazyDynaBean() throws Exception {
        LazyDynaBean hh = new LazyDynaBean();
        hh.set("country", "中国");
        hh.set("city", "北京");
        hh.set("postCode", "100120");
        hh.set("addr", "aaaaaaa");

        LazyDynaBean bb = new LazyDynaBean();
        bb.set("phone", "home", "11011011");
        bb.set("phone", "office", "111111");
        bb.set("email", "sh@126.com");
        bb.set("address", 0, hh);
        bb.set("birthDate", new GregorianCalendar(1990, 3, 29).getTime());

        LazyDynaBean tt = new LazyDynaBean();
        tt.set("userId", new Long(8888888));
        tt.set("gggg", "施杨");
        tt.set("user", "name", "李四");
        tt.set("password", "sgsgsgsg");
        //tt.set("dddd", bb);
        Map<String, String> describe = BeanUtils.describe(tt);
        long start = System.currentTimeMillis();
        System.out.println(gson.toJson(describe));
        System.out.println("end==" + (System.currentTimeMillis() - start));

        User user = new User();
        user.setName("zhangsan");
        DynaBean dynaBean = (DynaBean) user;
        dynaBean.set("test", "hhnnhh");
        System.out.println(gson.toJson(dynaBean));
    }

}
