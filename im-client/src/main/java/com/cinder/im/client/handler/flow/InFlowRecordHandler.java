package com.cinder.im.client.handler.flow;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Cinder
 * @Description: 入口流量统计
 * @Date create in 17:41 2020/7/19/019
 * @Modified By:
 */
public class InFlowRecordHandler extends ChannelInboundHandlerAdapter {
    private LongAdder inByteRecord = new LongAdder();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        inByteRecord.add(((ByteBuf) msg).readableBytes());
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // System.out.println("当前入口流量：" + inByteRecord.sum() + "字节！");
    }


}
