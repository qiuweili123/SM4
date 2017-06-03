package com.disruptor.test;

public class TradeTransaction {
    private String id;//交易ID  
    private double price;//交易金额  

    private int serailNo;

    public TradeTransaction() {
    }

    public TradeTransaction(String id, double price) {
        super();
        this.id = id;
        this.price = price;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * <p>Title: toString</p>
     * <p>Description: </p>
     *
     * @return
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "TradeTransaction [id=" + this.id + ", price=" + this.price + ", serailNo=" + this.serailNo + "]";
    }

}  
  
