package cn.slimsmart.rabbitmq.spring.rabbitmq.demo.async;

public class ReceiveMsgHandler {

    public void handleMessage(String text) {
        System.out.println(System.currentTimeMillis() + "Received: " + text);
    }
}
