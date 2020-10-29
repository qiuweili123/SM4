package com.java.basic;

/**
 * Created by lenovo on 2017/12/1.
 */
public class AboutCurrentTime {
    public static void main(String[] args) {
        //获得系统的时间，单位为毫秒,转换为妙
        long totalMilliSeconds = System.currentTimeMillis();
        long totalSeconds = totalMilliSeconds / 1000;
        System.out.println("totalSeconds==" + totalSeconds);
        //求出现在的秒
        long currentSecond = totalSeconds % 60;
        System.out.println("currentSecond==" + currentSecond);
        //求出现在的分
        long totalMinutes = totalSeconds / 60;
        System.out.println("totalMinutes==" + totalMinutes);
        long currentMinute = totalMinutes % 60;
        System.out.println("currentMinute==" + currentMinute);
        //求出现在的小时
        long totalHour = totalMinutes / 60;
        long currentHour = totalHour % 24;

        //显示时间
        System.out.println("总毫秒为： " + totalMilliSeconds);
        System.out.println(currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");
        long curTime = totalMilliSeconds / (1000 * 60 * 60 * 24);
        System.out.println(curTime);

    }
}
