package com.jeromq;

import org.zeromq.ZMQ;

/**
 * jeromq不支持
 */
public class inprocServer {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);


        ZMQ.Socket clients = context.socket(ZMQ.ROUTER);
        clients.bind("tcp://*:5559");


        ZMQ.Socket workers = context.socket(ZMQ.DEALER);
        workers.bind("inproc://workers");
        System.out.println("dddddd");
        for(int thread_nbr = 0;thread_nbr< 5;thread_nbr++)

        {
            Thread worker = new Worker(context, thread_nbr);
            System.out.println("start");
            worker.start();
        }
        // Connect work threads to client threads via a queue
        ZMQ.proxy(clients,workers,null);


        // We never get here but clean up anyhow
        clients.close();
        workers.close();
        context.term();
    }


}
