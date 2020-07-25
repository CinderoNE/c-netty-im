package com.cinder.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:33 2020/7/23/023
 * @Modified By:
 */
public class ImServerIdleStateHandler extends IdleStateHandler {
    private static final int READ_IDLE_TIME = 60;
    public ImServerIdleStateHandler() {
        super(READ_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println("服务端" + READ_IDLE_TIME + "秒未读到数据，关闭连接");
        ctx.channel().close();
    }
}
