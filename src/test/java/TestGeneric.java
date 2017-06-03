import com.java.generic.Generic;
import com.java.generic.IntSum;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class TestGeneric {

//	public static void main(String[] args) {
//		Generic  strGeneric=new Generic("ssd",3,4);
//	 
//		System.out.println("##strGeneric==");
//		strGeneric.showTypeName();
//	}

    /**
     * 测试泛型类多态的使用
     *
     * @param @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     * @Title: testIntSum
     * @Description: TODO
     */
    @Test
    public void testIntSum() throws Exception {
        IntSum ins = new IntSum();

        System.out.println(ins.sum(1, 2, 3, 4));
    }

    /**
     * 测试泛型方法
     *
     * @param @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     * @Title: testName
     * @Description: TODO
     */


    @Test
    public void testMethoed1() throws Exception {
        Generic gnGeneric = new Generic();

        //gnGeneric.showInfo2(String.class);
        //getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组
        Method[] methods = gnGeneric.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("##" + methods[i].getName());
            Type[] types = methods[i].getParameterTypes();
            for (int j = 0; j < types.length; j++) {
                System.out.println(types[j] + "##" + types[j].getClass().getSimpleName());
                if (types[j] instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) types[j];
                    Type type = pType.getActualTypeArguments()[0];
                    System.out.println((type instanceof Class) + "##" + type + "##type=dd=" + type.getClass().getSimpleName());
                }
            }
            //获取泛型类型
            Type[] ttypes = methods[i].getGenericParameterTypes();
            for (int j = 0; j < types.length; j++) {
                System.out.println(ttypes[j] + "##t111t=" + ttypes[j].getClass().getSimpleName());
                if (ttypes[j] instanceof ParameterizedType) {
                    ParameterizedType pType = (ParameterizedType) ttypes[j];
                    Type type = pType.getActualTypeArguments()[0];
                    System.out.println((type instanceof Class) + "##" + type + "##type==" + type.getClass().getSimpleName());
                }
            }
        }
    }

}
