package com.joda;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * LocalDate：只包含日期
 * LocalTime：只包含时间
 * Instant：时间轴上的时间点
 * DateTime：时区中完整的日期和时间
 * DateTimeZone：更好的时区
 * Duration和Period：持续时间
 * Interval：两个时间点之间的时间
 */

public class DateTimeTest {
    public static void main(String[] args) {
        getDateTime();
        plus();
        toLong();
        compare();
        getZeroTime();
        getPriod();
        getAge("2010-01-25");
    }

    public static void getDateTime() {
        DateTime currentTime = new DateTime();
        System.out.println(currentTime.toString("yyyy-MM-dd"));
    }

    public static void plus() {
        DateTime currentTime = new DateTime();
        String string = currentTime.plusMinutes(90).toString("yyyy-MM-dd HH:mm");
        System.out.println("String:" + string);
    }

    public static void toLong() {
        DateTime currentTime = new DateTime();
        Long value = Long.valueOf(currentTime.toString("yyyyMMddHHmm"));
        System.out.println(System.currentTimeMillis() + "value::" + value);
    }

    public static void compare() {
        DateTime currentTime = new DateTime();
        DateTime dateTime = new DateTime("2019-01-04");

        boolean afterNow = dateTime.isAfterNow();
        boolean timeAfter = currentTime.isAfter(dateTime);

        System.out.println("afterNow:" + afterNow + "timeAfter::" + timeAfter);
    }

    //获取0点的时间
    public static void getZeroTime() {
        DateTime dt = new DateTime(new Date()).withMillisOfDay(0);
        System.out.println(dt.toString("yyyyMMddHHmmSS"));
    }
    //获取时差
     public static void getPriod(){
         DateTime start=new DateTime("2005-12-01");
         DateTime end=new DateTime("2015-11-02");

         Period period=new Period(start,end);
         System.out.println("获取时差");
         System.out.println("month："+period.getMonths());
         System.out.println("days："+period.getDays());
         System.out.println("hours："+period.getHours());
         System.out.println("minutes："+period.getMinutes());
         System.out.println("second："+period.getSeconds());
        //另外的方式
         int years = Years.yearsBetween(start, end).getYears();
         int days = Days.daysBetween(start, end).getDays();
         System.out.println("yeas=="+years+"days"+days);
     }

    public static void getAge(String birthDay) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
        //时间解析
        LocalDate birthday = DateTime.parse(birthDay, format).toLocalDate();
        LocalDate now = LocalDate.now();
        Years age = Years.yearsBetween(birthday, now);
        System.out.println("now::"+now+" years:"+age.getYears());

    }
}
