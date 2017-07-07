package com.javapatterns.chain;

public class ChainTest {

    private final static String SUCCESS_KEY = "s";
    private final static String FAIL_KEY = "f";

    private String successKey = SUCCESS_KEY;
    private String failKey = FAIL_KEY;

    public void setSuccessKey(String successKey) {
        this.successKey = successKey;
    }

    public void setFailKey(String failKey) {
        this.failKey = failKey;
    }

    private static final String DEFULT_RES_KEY = "res";
    //向下传递处理请求
    private IRateHandler nextRateHandler;
    private IRateHandler preRateHandler;


    private ChainTest() {
        MonthRateHandler monthRateHandler = new MonthRateHandler();
        DayRateHandler dayRateHandler = new DayRateHandler();
        HourRateHandler hourRateHandler = new HourRateHandler();
        MinRateHandler minRateHandler = new MinRateHandler();
        SecRateHandler secRateHandler = new SecRateHandler();
        //向下传递
        monthRateHandler.setNextHandler(dayRateHandler).setNextHandler(hourRateHandler).setNextHandler(minRateHandler).setNextHandler(secRateHandler).setNextHandler(null);
        secRateHandler.setPreHandler(minRateHandler).setPreHandler(hourRateHandler).setPreHandler(dayRateHandler).setPreHandler(monthRateHandler).setPreHandler(null);
        nextRateHandler = monthRateHandler;
        preRateHandler = secRateHandler;
    }

    public static void main(String[] args) {

    }
}
