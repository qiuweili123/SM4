package com.sh.taskExecutor;

public class TaskProcessor implements Runnable {
    private String name;

    public TaskProcessor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //private service service;
    @Override
    public void run() {
        //service.dosomthing()

        try {
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis() + "currentTradName:" + Thread.currentThread().getName() + "sd=" + getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
