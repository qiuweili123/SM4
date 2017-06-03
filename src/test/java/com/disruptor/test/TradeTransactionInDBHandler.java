package com.disruptor.test;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;


public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>, WorkHandler<TradeTransaction> {
    private String workName;

    public TradeTransactionInDBHandler() {

    }

    public TradeTransactionInDBHandler(String workName) {
        this.workName = workName;
    }

    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        event.setId("C2" + event.getId());
        System.out.println("C2##" + Thread.currentThread().getName() + "##sequence=" + sequence + "##" + "##event=" + event + "##this.workName=" + this.workName);
        this.onEvent(event);
        Thread.sleep(1000);
    }

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        //这里做具体的消费逻辑   
        event.setId(UUID.randomUUID().toString());//简单生成下ID  

    }
}  
  