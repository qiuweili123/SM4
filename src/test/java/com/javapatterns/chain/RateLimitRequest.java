package com.javapatterns.chain;


/**
 * Created by ly on 2017/5/7.
 */
public class RateLimitRequest {
    private String resNode;

    /**
     * 是否超过资源限制
     */
    private Boolean notSurpassLimit = true;

    public String getResNode() {
        return resNode;
    }

    public void setResNode(String resNode) {
        this.resNode = resNode;
    }


    public Boolean isNotSurpassLimit() {
        return this.notSurpassLimit;
    }

    public void setNotSurpassLimit(Boolean notSurpassLimit) {
        this.notSurpassLimit = notSurpassLimit;
    }


    public RateLimitRequest(String resNode) {
        this.resNode = resNode;
    }


}
