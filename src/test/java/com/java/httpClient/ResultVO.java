/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: ResultVO.java
 * @Package com.java.httpClient
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2015年11月3日 上午8:47:08
 * @version
 */
package com.java.httpClient;

/**
 * @author liqiuwei
 * @create time:2015年11月3日上午8:47:08
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class ResultVO {

    private int ret;

    private String msg;

    /**
     * @return the ret
     */
    public int getRet() {
        return this.ret;
    }

    /**
     * @param ret the ret to set
     */
    public void setRet(int ret) {
        this.ret = ret;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
