package com.disruptor.test;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(2000);
        event.setId("C1" + event.getId());
        System.out.println("C1##" + Thread.currentThread().getName() + "##sequence=" + sequence + "##" + "##event=" + event + "##endOfBatch=" + endOfBatch);


    }
}
      