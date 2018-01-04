package com.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2018/1/3.
 * java的Introspector获取类的整个描述
 */
public class TestIntrospector {

    public static void main(String[] args) throws Exception {
//      method();
        methid1();
    }

    /*
     * 简单Introspector实例应用
     *
     * 一般情况获取：
     *      String propertyValue="a";
     *      "a"--->"A"--->"getA()"--->再通过反射，method获取值
     * 通过PropertyDescriptor类，进行反射获取
     *
     */
    public static void method() throws Exception {
        User bean = new User(3, "lisi");
        String propertyName = "name";//定义JavaBean中的属性

        //get,获取JavaBean中的属性值
        Object retVal = getProperty(bean, propertyName);//通过选中，单击右键--》Refactor--》Extract Method就可以抽取为方法
        System.out.println(retVal);

        //set,设置JavaBean中的属性值
        Object setProperty = "wang";
        setPropety(bean, propertyName, setProperty);//同样方法设置，抽取为方法
        System.out.println(bean.getName());

        //把上面的三句话合并成一句话
        new PropertyDescriptor(propertyName, bean.getClass()).getWriteMethod().invoke(bean, new Object[]{"zhangshang"});
        System.out.println(bean.getName());
    }

    //通过这样抽取为方法，以后可以直接用set/getProperty了，不用老是写，提高代码复用率
    private static void setPropety(User bean, String propertyName,
                                   Object setProperty) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        PropertyDescriptor pd1 = new PropertyDescriptor(propertyName, bean.getClass());
        Method m_set = pd1.getWriteMethod();
        m_set.invoke(bean, setProperty);
    }

    //通过这样抽取为方法，以后可以直接用set/getProperty了，不用老是写，提高代码复用率,此种比较简单
    private static Object getProperty(Object bean, String propertyName)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, bean.getClass());//PropertyDescriptor类用来操作JavaBean
        Method m = pd.getReadMethod();
        Object retVal = m.invoke(bean);//返回bean这个对象的方法
        return retVal;
    }


    /*
     * 复杂Introspector应用实例
     *
     * 就是调用Introspector.getBeanInfo方法，得到的BeanInfo对象封装了把这个类当做JavaBean看的结果信息
     * getBeanInfo方法为Introspector类中的静态方法，所以可以直接调用
     */
    public static void methid1() throws Exception {
        User bean = new User(3, "lisi");
        String propertyName = "name";//定义JavaBean中的属性
//      BeanInfo bi=Introspector.getBeanInfo(bean.getClass());
//      PropertyDescriptor[]pds=bi.getPropertyDescriptors();
//      for (PropertyDescriptor pd : pds) {
//          if(pd.getName().equals(propertyName)){
//              Method m=pd.getReadMethod();
//              m.invoke(bean);
//              break;
//          }
//      }
        //这个方法就是根据上面注释的语句抽取出来的，为了跟上面的方法不发生冲突，我加了个后缀"_fuza"
        Object getProperty = getProperty_fuza(bean, propertyName);
        System.out.println("直接获取属性a的值：" + getProperty);

        Object setProperty = "wang";
        setProperty(bean, propertyName, setProperty);
        System.out.println("设定属性a值后的：" + bean.getName());
    }

    //通过抽取出来的，复杂一点的setProperty
    private static void setProperty(User bean, String propertyName,
                                    Object setProperty) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        BeanInfo bi_set = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] pds_set = bi_set.getPropertyDescriptors();
        for (PropertyDescriptor pd_set : pds_set) {
            if (pd_set.getName().equals(propertyName)) {
                Method m_set = pd_set.getWriteMethod();
                m_set.invoke(bean, setProperty);
                break;
            }
        }
    }

    //比上面的方法稍微复杂一点的方式
    private static Object getProperty_fuza(User bean, String propertyName)
            throws IntrospectionException, IllegalAccessException,
            InvocationTargetException {
        Object getProperty = null;
        BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (pd.getName().equals(propertyName)) {
                Method m = pd.getReadMethod();
                getProperty = m.invoke(bean);
                break;
            }
        }
        return getProperty;
    }
}
