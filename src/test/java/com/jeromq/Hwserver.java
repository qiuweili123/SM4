package com.jeromq;

import org.zeromq.ZMQ;

public class Hwserver {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to clients
        ZMQ.Socket responder = context.socket(ZMQ.REP);
        responder.bind("tcp://*:5555");
        while (!Thread.currentThread().isInterrupted()) {
            // Wait for next request from the client
            byte[] request = responder.recv(0);
            System.out.println("Received Hello");
            // Do some 'work'
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Send reply back to client
            String reply = "World";
            responder.send(reply.getBytes(), 0);
        }
        System.out.println("###end---");
        responder.close();
        context.term();
    }

}
