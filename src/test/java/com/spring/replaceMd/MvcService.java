package com.spring.replaceMd;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MvcService {
    public String getTime(String name) {
        System.out.println("----" + name);
        SimpleDateFormat formate = new SimpleDateFormat("yy-MM-dd");
        return formate.format(new Date());
    }
}