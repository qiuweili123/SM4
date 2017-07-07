package com.javapatterns.chain;

/**
 * Created by ly on 2017/5/7.
 */
public interface IRateHandler {

    IRateHandler setNextHandler(IRateHandler nextHandler);

    IRateHandler setPreHandler(IRateHandler preHandler);

    /**
     * 使用责任链处理某一资源</br>
     * 处理的规则为月、天、小时、分钟、秒
     *
     * @param request
     */
    void processRes(RateLimitRequest request);

    /**
     * 反向回滚资源,将原来资源进行减掉</br>
     * 处理的规则为秒、分钟、小时、天、月
     *
     * @param request
     */
    void rollBackRes(RateLimitRequest request);


}
