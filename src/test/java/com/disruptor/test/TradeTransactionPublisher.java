package com.disruptor.test;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TradeTransactionPublisher implements Runnable {
    private static int LOOP = 10;//模拟一千万次交易的发生
    Disruptor<TradeTransaction> disruptor;
    private CountDownLatch latch;

    public TradeTransactionPublisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeTransactionEventTranslator tradeTransloator = new TradeTransactionEventTranslator();
        ;
        for (int i = 0; i < LOOP; i++) {

            tradeTransloator.setSerailNo(i);
            disruptor.publishEvent(tradeTransloator);
            System.out.println("##" + disruptor.getBufferSize() + "##" + disruptor.getCursor());
/*             try {
                Thread.sleep(1000);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
        }
        System.out.println("##name==" + Thread.currentThread().getName());
        latch.countDown();
    }

}

class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
    private Random random = new Random();
    private int serailNo;

    /**
     * @return the serailNo
     */
    public int getSerailNo() {
        return this.serailNo;
    }

    /**
     * @param serailNo the serailNo to set
     */
    public void setSerailNo(int serailNo) {
        this.serailNo = serailNo;
    }

    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        this.generateTradeTransaction(event);
    }

    private TradeTransaction generateTradeTransaction(TradeTransaction trade) {
        trade.setPrice(random.nextDouble() * 9999);
        trade.setSerailNo(serailNo);
        return trade;
    }
}  
  
  
