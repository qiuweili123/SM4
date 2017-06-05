package com.spring.replaceMd;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 17-6-5.
 */
public class MvcServiceReplaceImpl implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        SimpleDateFormat formate = new SimpleDateFormat("yy-MM-dd HH:mm:ss.SS");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 2);
        System.out.println(o + "##" + method.getName());
        return formate.format(c.getTime());
    }
}
