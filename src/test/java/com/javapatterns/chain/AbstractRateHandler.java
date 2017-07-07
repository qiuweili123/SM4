package com.javapatterns.chain;

/**
 * Created by ly on 2017/5/7.
 */

public abstract class AbstractRateHandler implements IRateHandler {
    /**
     * 向下传递资源
     */
    private IRateHandler nextHandler;
    /**
     * 向上回滚资源
     */
    private IRateHandler preHandler;

    /*  public AbstractRateHandler(IRateHandler nextHandler) {
          this.nextHandler = nextHandler;
      }
  */
    public IRateHandler setNextHandler(IRateHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public IRateHandler setPreHandler(IRateHandler preHandler) {
        this.preHandler = preHandler;
        return preHandler;
    }

    @Override
    public void processRes(RateLimitRequest request) {
        System.out.println("##next==" + this);
        boolean b = request.isNotSurpassLimit();
        if (b) {
            if (nextHandler != null) {
                nextHandler.processRes(request);
            }
        } else {
            if (preHandler != null) {
                preHandler.rollBackRes(request);
            }
        }
    }

    @Override
    public void rollBackRes(RateLimitRequest request) {

    }

}
