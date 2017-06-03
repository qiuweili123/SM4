package com.disruptor.test;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("D##" + Thread.currentThread().getName() + "##sequence=" + sequence + "##" + "##event=" + event + "##endOfBatch=" + endOfBatch);
    }
}  
  
  
  
