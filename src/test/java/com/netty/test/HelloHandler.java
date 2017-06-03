/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: HelloHandler.java
 * @Package com.netty.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年6月13日 下午10:38:59
 * @version
 */
package com.netty.test;

import org.jboss.netty.channel.*;

/**
 * @author liqiuwei
 * @create time:2016年6月13日下午10:38:59
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class HelloHandler extends SimpleChannelHandler {

    /**
     * <p>Title: messageReceived</p>
     * <p>Description: </p>
     *
     * @param ctx
     * @param e
     * @throws Exception
     * @see SimpleChannelHandler#messageReceived(ChannelHandlerContext, MessageEvent)
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("recive");
        System.out.println("输入的信息为" + e.getMessage());
        ctx.getChannel().write("recived msg");//输出响应信息
        // TODO Auto-generated method stub
        super.messageReceived(ctx, e);
    }

    /**
     * <p>Title: exceptionCaught</p>
     * <p>Description: </p>
     *
     * @param ctx
     * @param e
     * @throws Exception
     * @see SimpleChannelHandler#exceptionCaught(ChannelHandlerContext, ExceptionEvent)
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exception");
        // TODO Auto-generated method stub
        super.exceptionCaught(ctx, e);
    }

    /**
     * <p>Title: channelConnected</p>
     * <p>Description: </p>
     *
     * @param ctx
     * @param e
     * @throws Exception
     * @see SimpleChannelHandler#channelConnected(ChannelHandlerContext, ChannelStateEvent)
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelconnectioned");

        // TODO Auto-generated method stub
        super.channelConnected(ctx, e);
    }

    /**
     * <p>Title: channelDisconnected</p>
     * <p>Description: </p>
     *
     * @param ctx
     * @param e
     * @throws Exception
     * @see SimpleChannelHandler#channelDisconnected(ChannelHandlerContext, ChannelStateEvent)
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        // TODO Auto-generated method stub
        super.channelDisconnected(ctx, e);
    }

    /**
     * <p>Title: channelClosed</p>
     * <p>Description: </p>
     *
     * @param ctx
     * @param e
     * @throws Exception
     * @see SimpleChannelHandler#channelClosed(ChannelHandlerContext, ChannelStateEvent)
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("chanenlClosed");
        super.channelClosed(ctx, e);
    }

}
