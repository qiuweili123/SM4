package com.java.basic;

/**
 * Created by lenovo on 2017/12/1.
 */
public class AboutTryReturn {
    public static void main(String[] args) {
        int i = NoException();
        System.out.println(i);

    }

    public static int NoException() throws RuntimeException {
        int i = 10;
        try {
            System.out.println("i in try block is" + i);
            i = i / 0;
            //  return --i;
        } catch (Exception e) {
            System.out.println("i in catch - form try block is" + i);
            i = 6;

            //throw new RuntimeException("dddd"); 终止后边代码执行
        }


        return 5;
    }
}
