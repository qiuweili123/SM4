package com.sh.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;


public class QueueOneListener implements MessageListener {


    public void onMessage(Message message) {
        try {
            String s = new String(message.getBody());
            if (s.equals("\"word2\"")) {
                throw new Exception("sdsdsdsd");
            }
            System.out.println("data==" + new String(message.getBody()) + "" + message.getMessageProperties());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void showMessage(Message message) {
        //String s=new String(message.getBody());
        try {
            System.out.println("sss" + message);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }


    }

}
