package com.java.basic;

import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * java反射
 *
 * @author liqiuwei
 * @version $Id: AboutReflect.java, v 0.1 2017年3月23日 下午3:17:04 liqiuwei Exp $
 */
public class AboutReflect {
    public static void main(String[] args) {
        getMethodInfo("com.java.basic.InnerInterface");
    }

    /**
     * 传入全类名获得对应类中所有方法名和参数名
     */
    @SuppressWarnings("rawtypes")
    private static void getMethodInfo(String pkgName) {
        try {
            Class clazz = Class.forName(pkgName);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                System.out.println("方法名称:" + methodName);
                Class<?>[] parameterTypes = method.getParameterTypes();

                Path path = method.getAnnotation(Path.class);
                System.out.println("path" + path);
                final Parameter[] parameters = method.getParameters();
                // 返回一个注解类型的二维数组,每一个方法的参数包含一个注解数组。
                Annotation[][] pannotations = method.getParameterAnnotations();
                String[] paramNames = new String[pannotations.length];
                Annotation annotation;
                System.out.println("##pannotations==" + pannotations.length);
                if (pannotations.length > 0) {
                    for (int i = 0; i < pannotations.length; i++) {
                        System.out.println("i annotation==" + pannotations[i].getClass().getName() + "##" + pannotations[i].length);
                        for (int j = 0; j < pannotations[i].length; j++) {
                            // QueryParam queryParam = (QueryParam)
                            // pannotations[i][j];
                            annotation = pannotations[i][j];

                            if (annotation instanceof FormParam) {
                                System.out.println("##" + ((FormParam) annotation).value());
                            }
                            System.out.println(annotation.annotationType().getSimpleName() + "##i=" + i + "##j=" + j + "##value=" + annotation.annotationType());
                            // paramNames[i] = queryParam.value();
                        }

                    }
                }
                System.out.println("Array==" + Arrays.toString(paramNames));

                for (final Parameter parameter : parameters) {
                    Annotation[] annonations = parameter.getAnnotations();
            /*
             * for (Annotation annotation : annonations) {
		     * System.out.println("nnooo==" +
		     * annotation.getClass().getSimpleName()); }
		     */

                    System.out.println("\targ : " + (parameter.isNamePresent() ? parameter.getName() : "Parameter Name not provided,") + (Modifier.isFinal(parameter.getModifiers()) ? " IS " : " is NOT ") + "final, type " + parameter.getType().getCanonicalName() + ", and parameterized type of " + parameter.getParameterizedType() + " and " + (parameter.isVarArgs() ? "IS " : "is NOT ") + "variable." + "##isWrapClass==" + isWrapClass(parameter.getType()));
                }
                for (Class<?> clas : parameterTypes) {
                    String parameterName = clas.getName();
                    System.out.println("参数名称:" + parameterName);
                }
                System.out.println("*****************************");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

}
