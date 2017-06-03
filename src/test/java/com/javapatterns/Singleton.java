package com.javapatterns;

/**
 * 这段代码中，因为Singleton没有static的属性，因此类加载的时候并不会被初始化。直到调用getInstance()的时候，会首先加载SingleHoder类，这个类有一个static的INSTANCE实例，
 * 因此需要调用Singleton的构造方法，然后getInstance()将把这个内部类的instance返回给使用者。由于这个instance是static的，因此并不会构造多次。
 * 由于SingleHoder是私有静态内部类，所以不会被其他类知道，同样，static语义也要求不会有多个实例存在。并且，JSL规范定义，类的构造必须是原子性的，非并发的，因此不需要加同步块。
 * 同样，由于这个构造是并发的，所以getInstance()也并不需要加同步。
 * 补充，如果使用syncnized来实现线程安全，必然在高并发的情况下产生性能问题，所以推荐使用内部类实现懒加载。
 * <p>
 * 内部类只有被引用的时候才被装载
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: Singleton
 * @Description: TODO
 * @date 2015年8月8日 下午9:45:07
 */
public class Singleton {
    private Singleton() {
        System.out.println("构造Singleton");
        //throw new Exception("不能被构造");
        //new Exception("");

    }

    public static Singleton getInstance() {

        return SingleHoder.INSTANCE;
    }

    public static void showInfo() {
        System.out.println("say hello!");
    }

    private static class SingleHoder {
        //INSTANCE并不是编译期常量，而是执行期常量，因为他们的初值只有在执行的  时候才能确定，一旦确定不能再变
        //而INSTANCE同时使用static类型，只有在装载class的候才进行初始化，不会在每次产生新的对象的时候都再初始化一次
        private static final Singleton INSTANCE = new Singleton();

        static {
            System.out.println("##SingleHoder==构造");
        }
    }

}
