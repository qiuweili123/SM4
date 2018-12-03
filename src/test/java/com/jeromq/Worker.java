package com.jeromq;

import org.zeromq.ZMQ;

public class Worker extends Thread {
    private ZMQ.Context context;
    private int workerNum;


    Worker(ZMQ.Context context, int worker) {
        this.context = context;
        this.workerNum = worker;
    }

    @Override
    public void run() {
        ZMQ.Socket socket = context.socket(ZMQ.REP);
        socket.connect("inproc://workers");

        while (true) {
// Wait for next request from client (C string)
            String request = socket.recvStr(0);
            System.out.println(Thread.currentThread().getName() + " Received request: [" + request + "]");

// Do some 'work'
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            // Send reply back to client (C string)
            socket.send("work" + this.workerNum + "reply is: " + "world", 0);
        }
    }
}
 