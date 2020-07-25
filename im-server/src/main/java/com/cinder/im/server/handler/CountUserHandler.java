package com.cinder.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Cinder
 * @Description:
 * @Date create in 0:18 2020/7/19/019
 * @Modified By:
 */
@ChannelHandler.Sharable
public class CountUserHandler extends ChannelInboundHandlerAdapter {
    private CountUserHandler(){
    }
    public static final CountUserHandler INSTANCE = new CountUserHandler();
    public static final AtomicInteger USER_COUNT = new AtomicInteger(0);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "加入连接");
        USER_COUNT.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "断开连接");
        USER_COUNT.decrementAndGet();
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("发生异常，原因：" + cause.getMessage());
    }
}
