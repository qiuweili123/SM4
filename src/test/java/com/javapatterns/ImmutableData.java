package com.javapatterns;

/**
 * 使用不变模式进行线程访问，提高效率
 * 类和属性定义全部为final
 * <p>
 * <p>
 * 该类一个实例是共享资源类，一旦声明实例不在变化需要状态永久不变，因为没有seter方法
 * <p>
 * 一旦一个属性声明成final，就是Myeclipse的自动生成getter setter方法也会自动忽略到setter方法的
 * <p>
 * 这个类的所有设计都以始终不变为目的，所有，ImmutableData中的所有方法也不用加上synchinored关键字，这样
 * <p>
 * 可以提高程序的性能
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: ImmutableData
 * @Description: TODO
 * @date 2015年8月15日 下午8:21:58
 */
public final class ImmutableData {//不变的数据
    private final int data;

    public ImmutableData(int data) {
        super();
        this.data = data;
    }


    public int getData() {
        return data;
    }


    @Override
    public String toString() {
        return "ImmutableData s数据为[data=" + data + "]";
    }

}

