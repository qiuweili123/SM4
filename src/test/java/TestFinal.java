import com.java.basic.AboutFinal;
import com.javapatterns.ImmutableData;

import java.util.ArrayList;
import java.util.List;

public class TestFinal {
    public static void main(String[] args) {
        //final修饰基本数据类型变量时，只能赋值一次,再赋重值也不行，变量的值是不可改变的,修饰引用数据类型对象时对象的地址是
        //不可变的，但对象中的成员属性可变。
        AboutFinal a = new AboutFinal();
        a = new AboutFinal(56);
        System.out.println(a.hashCode());
        //不变模式 强不变模式 没有数子类，所有实例全部引用一个地址
        ImmutableData data1 = new ImmutableData(5);
    /*

	 * 该类是共享资源类，需要状态永久不变

	 * 一旦一个属性声明成final，就是Myeclipse的自动生成getter setter方法也会自动忽略到setter方法的

	 * 这个类的所有设计都以始终不变为目的，所有，ImmutableData中的所有方法也不用加上synchinored关键字，这样

	 * 可以提高程序的性能

	 */
        System.out.println(data1.hashCode() + "sd" + new ImmutableData(6).hashCode());

        //tmp
        List list =new ArrayList(5);
        System.out.println(list.size());
    }
}
