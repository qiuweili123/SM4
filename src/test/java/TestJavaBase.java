import java.math.BigInteger;
import java.util.*;

public class TestJavaBase {
    public static void main(String[] args) {
        //	int i=1,a=1;
        //	++i;
        //	a++;
        //	System.out.println((++i)+"#"+(++i)+"##"+(++i));
        //testArrays();
        //testDelList();
        //calculte();
        //testBigInteger();
        //getChar();
        System.out.println("##ss" + testSubstr());
    }

    public static int fn1(int i) {

        int tmp = i;
        i = tmp + 1;
        return tmp;
    }

    public static int fn2(int i) {

        int tmp = i;
        tmp = tmp + 1;
        return tmp;
    }

    /**
     * 其实这里的Arrays.asList返回的是ArrayList的静态对象，本质上还是数组，没有实现add方法，不能，它的大小也是固定的
     *
     * @Description:TODO(这里用一句话描述这个方法的作用)
     * @return: void
     * @author:liqiuwei 2015年10月23日上午10:25:39
     * @update1:updater:liqiuwei updatetime:2015年10月23日上午10:25:39 TODO(描述修改内容)
     */
    public static void testArrays() {
        String[] str_arry = new String[]{"h", "e", "w"};
        List<String> list = Arrays.asList(str_arry);
        list.add("hht");
        Set set = new HashSet<>(list);
        System.out.println("##list==" + list);

    }

    public static void testDelList() {
        ArrayList list = new ArrayList(Arrays.asList("a", "b", "c", "d"));
        for (int i = 0; i < list.size(); i++) {

            list.remove(i);
        }
        System.out.println(list);

    }

    public static void calculte() {
        int i = 5;
        System.out.println("###" + (i % 3) + "##" + (i / 2));
    }

    public static void testBigInteger() {
        BigInteger big = new BigInteger("22222222222222233434343");
        System.out.println("##" + big.mod(BigInteger.valueOf(5)));
    }

    public static void getChar() {
        String str = "A123456";
        System.out.println("#####" + str.charAt(0));
    }

    public static int testSubstr() {
        String str = "ab";
        int c = 2;
        try {

            System.out.println(str.length() + "1212==" + str.substring(str.length() - 1, str.length()));
        } catch (Exception e) {
            c = 1;
            e.printStackTrace();

            // TODO: handle exception
        }/*finally {
            c=9;
		} */


        return c;

    }

}

