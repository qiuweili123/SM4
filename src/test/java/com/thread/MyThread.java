package com.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

class MyThread extends Thread {

    private String id;

    /**
     *
     */
    public MyThread(String id) {
        // TODO Auto-generated constructor stub
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(id + "\t正在执行......  Time:" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        try {
            this.sleep(40 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(id + "\t关闭......  Time:" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

}