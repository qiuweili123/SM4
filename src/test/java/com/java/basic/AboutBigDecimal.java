package com.java.basic;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Objects;

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
    // 默认小数运算精度
    private static final int DEF_DIV_SCALE = 2;

    private static final BigDecimal DEF_V = new BigDecimal("0");
    // 默认四舍五入
    private static final int ROUND_MODE = BigDecimal.ROUND_HALF_UP;
    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        /*
         * BigDecimal b1 = new BigDecimal(Double.valueOf(value1)); BigDecimal b2
		 * = new BigDecimal(Double.valueOf(value2));
		 */
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }

    public static BigDecimal getScaleValue(BigDecimal o) {
        BigDecimal n = Objects.isNull(o) ? DEF_V : o;
        return n.setScale(DEF_DIV_SCALE, ROUND_MODE);
    }

    public static BigDecimal addDecimal(BigDecimal value1, BigDecimal value2) {
        BigDecimal d1 = new BigDecimal(0);
        if (value1 == null) {
            d1 = value1;
        }
        BigDecimal vae1 = d1.setScale(2, BigDecimal.ROUND_HALF_UP);

        /*
         * BigDecimal b1 = new BigDecimal(Double.valueOf(value1)); BigDecimal b2
		 * = new BigDecimal(Double.valueOf(value2));
		 */
        //  value2.setScale(2);
        BigDecimal vae = value2.setScale(2, BigDecimal.ROUND_HALF_UP);

        return d1.add(vae);
    }

    public static int compare(BigDecimal value1, BigDecimal value2) {
        BigDecimal b1 = getScaleValue(value1);
        BigDecimal b2 = getScaleValue(value2);
        return b1.compareTo(b2);
    }

    public static Double addDuble(double value1, double value2) {


        return value1 + value2;
    }

    public static void main(String[] args) {
        System.out.println(add(0.06, 0.01));
        BigDecimal aDouble = new BigDecimal(1.22);
        float afloat=12.35f;new Float(1.22);
        System.out.println("construct with a double value: " + aDouble + "getscale value=" + getScaleValue(aDouble) + "#value==" + BigDecimal.valueOf(1.22)+"##"+afloat);
        BigDecimal aString = new BigDecimal("1.22");
        Number number1 = NumberUtils.createNumber("1.0");

        Number number2 = NumberUtils.createNumber("2.0");

        System.out.println("compare=="+compare(number1,number2));
        System.out.println("construct with a String value: " + aString+"::number=="+number1);

        //  System.out.println(addDecimal(null,new BigDecimal("1.22")));
        BigDecimal bDouble = new BigDecimal(1.32);
        System.out.println(aDouble.compareTo(aString) + "#####" + compare(aDouble, bDouble));

        Float f1 = 200f;
        Float f2 = 200f;
        System.out.println("f1 == f2:" + (f1.floatValue()==f2.floatValue()));
    }

    public static int compare(Number x, Number y){
        if(x instanceof  Long){
            System.out.println("floaddd");
        }else
        {
            System.out.println("dddddss");
        }
         if(x==y){
            return 0;
         }


 return 1;

    }
}