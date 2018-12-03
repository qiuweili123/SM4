package com.jeromq;

import org.zeromq.ZMQ;

/**
 * Request-Reply模式:Request-Reply模式是严格同步的，Request端必须先发送后接受，reply端必须先接受后发送
 */
public class Hwclient {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        //  Socket to talk to server
        System.out.println("Connecting to hello world server…");
        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://localhost:5555");
        for(int requestNbr = 0; requestNbr != 100; requestNbr++) {
            String request = "Hello";
            System.out.println("Sending Hello " + requestNbr);
            requester.send(request.getBytes(), 0);
            byte[] reply = requester.recv(0);
            System.out.println("Received " + new String(reply) + " " + requestNbr);
        }
        requester.close();
        context.term();
    }

}
