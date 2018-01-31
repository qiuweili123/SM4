package com.java.basic;

/**
 * Created by lenovo on 2017/12/1.
 */
public class AboutTryReturn {
    public static void main(String[] args) {

        System.out.println(NoException());

    }

    public static int NoException() throws RuntimeException {
        int i = 10;
        try {
            System.out.println("i in try block is" + i);
            i = i / 0;
           // return --i;
        } catch (Exception e) {
            System.out.println("i in catch - form try block is" + i);
            throw new RuntimeException("dddd");
        }
        return 5;

    }
}
