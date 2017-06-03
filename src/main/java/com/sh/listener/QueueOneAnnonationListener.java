package com.sh.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class QueueOneAnnonationListener {
    public QueueOneAnnonationListener() {
        System.out.println("33333333333333333333");
    }

    //	public void showMessage(String obj){
//		//String s=new String(message.getBody());
//		try {
//			System.out.println("sss"+obj);
//			 
//		} catch (Exception e) 
//		{
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		
//		 
//	}
    @RabbitListener(queues = "queue_one", exclusive = false, containerFactory = "containerFactory")
    public void showMessage(String mesString) {
        System.out.println("#eeeeeeeeeeee");
        // TODO Auto-generated method stub
        //System.out.println("##ddd"+message.getBody());

    }
    // @RabbitListener(queues = "queue_one", exclusive = false,containerFactory="rabbitListenerContainerFactory",admin="erpAdmin")
    // @SendTo("my-mq-exchange/queue.one")

//	 public void onMessage(Message message) {
//		 
//		 System.out.println("sdsds");
////		 String data = new String(message.getBody());  
////	        System.out.println("queue_one data:" + data);  
//		// TODO Auto-generated method stub
//
//	}


}
