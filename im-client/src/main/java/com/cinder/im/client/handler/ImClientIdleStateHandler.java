package com.cinder.im.client.handler;

import com.cinder.im.client.util.ConnectUtil;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:09 2020/7/23/023
 * @Modified By:
 */
public class ImClientIdleStateHandler extends IdleStateHandler {
    private static final int READ_IDLE_TIME = 30;
    public ImClientIdleStateHandler() {
        super(READ_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println("客户端" + READ_IDLE_TIME + "秒未读到数据");
        System.out.println("关闭连接");
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        ctx.disconnect();


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("发生错误，原因：" + cause);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("通道被关闭");
        super.channelInactive(ctx);
    }
}
