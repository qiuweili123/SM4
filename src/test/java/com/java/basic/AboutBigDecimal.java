package com.java.basic;

import java.math.BigDecimal;

/**
 * (1)当double必须用作BigDecimal的源时，请注意，此构造方法提供了一个准确转换；它不提供与以下操作相同的结果：先使用Double.
 * toString(double)方法，然后使用BigDecimal(String)构造方法，将double转换为String。要获取该结果，
 * 请使用static valueOf(double)方法。<br>
 * (2)尽量使用参数类型为String的构造函数。 <br>
 * (3) BigDecimal都是不可变的（immutable）的，在进行每一步运算时，都会产生一个新的对象，所以在做加减乘除运算时千万要保存操作后的值。
 * <p>
 *
 * @author ard-liqiuwei
 * @create time:2017年3月7日下午7:19:33
 */
public class AboutBigDecimal {
    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(double value1, double value2) {
        /*
         * BigDecimal b1 = new BigDecimal(Double.valueOf(value1)); BigDecimal b2
		 * = new BigDecimal(Double.valueOf(value2));
		 */
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2);
    }

    public static void main(String[] args) {
        System.out.println(add(0.06, 0.01));
        BigDecimal aDouble = new BigDecimal(1.22);
        System.out.println("construct with a double value: " + aDouble + "#value==" + BigDecimal.valueOf(1.22));
        BigDecimal aString = new BigDecimal("1.22");
        System.out.println("construct with a String value: " + aString);
    }
}