import com.javapatterns.FruitFactoryEnum;
import com.javapatterns.abstractfactory.Fruit;

/**
 * 枚举测试类
 *
 * @author inth-liqiuwei
 * @version V1.0
 * @ClassName: TestEnum
 * @Description: TODO
 * @date 2015年8月5日 上午10:10:25
 */
public class TestEnum {

    public static void main(String[] args) {
        System.out.println(SeasonEnum.spring + "##" + SeasonEnum.position + "##" + SeasonEnum.getSeason());
        System.out.println("" + Oparate.MINUS.operate(1, 3));
        System.out.println("测试value of=" + Oparate.getOparateEnum("minus").operate(4, 5));

        Fruit f = FruitFactoryEnum.valueOf("greenApple".toUpperCase()).createFruit();
        Fruit f2 = FruitFactoryEnum.valueOf("greenApple".toUpperCase()).createFruit();

        System.out.println("##FruitFactoryEnum==" + f);
        System.out.println("##FruitFactoryEnum==" + (f == f2));

        System.out.println(FruitFactoryEnum.GREENAPPLE.name());

    }

    /**
     * 枚举像普通的类一样可以添加属性和方法，可以为它添加静态和非静态的属性或方法
     *
     * @author jiqinlin
     */
    public enum SeasonEnum {
        // 注：枚举写在最前面，否则编译出错
        spring, summer, autumn, winter;

        /**
         * @Fields position :添加属性
         */
        private final static String position = "test";

        public static SeasonEnum getSeason() {
            if ("test".equals(position)) return spring;
            else return winter;
        }

        /**
         * <p>
         * Title: toString
         * </p>
         * <p>
         * Description: 重写toString方法
         * </p>
         *
         * @return
         * @see Enum#toString()
         */

        @Override
        public String toString() {
            switch (this) {
                case spring:
                    return "chun tian";
                case summer:
                    return "xia tian";

                default:
                    return "qita";
            }
        }
    }

    /**
     * 性别
     * <p>
     * 实现带有构造器的枚举
     *
     * @author jiqinlin
     */
    public enum Gender {
        // 通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
        // 赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
        MAN("MAN"), WOMEN("WOMEN");

        private final String value;

        // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 带有抽象方法的枚举
     *
     * @author inth-liqiuwei
     * @version V1.0
     * @ClassName: Oparate
     * @Description: 每个枚举值为定义的枚举类对象，每个枚举对象通过匿名内部类实现抽象方法
     * @date 2015年8月5日 上午10:25:55
     */
    public enum Oparate {
        PLUS {
            @Override
            public int operate(int x, int y) {
                // TODO Auto-generated method stub
                return x + y;
            }

        }, MINUS {
            @Override
            public int operate(int x, int y) {
                // TODO Auto-generated method stub
                return x - y;
            }

        };

        /**
         * valueOf的能将值转换为对应的枚举对象
         *
         * @param values
         * @return
         * @author inth-liqiuwei
         * @date 2015年8月5日 上午10:49:24
         */
        public static Oparate getOparateEnum(String values) {

            return valueOf(values.toUpperCase());
        }

        public abstract int operate(int x, int y);

    }

}
