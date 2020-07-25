package com.cinder.im.client.handler.flow;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Cinder
 * @Description: 出口流量记录
 * @Date create in 16:55 2020/7/19/019
 * @Modified By:
 */
public class OutFlowRecordHandler extends ChannelOutboundHandlerAdapter {
    private LongAdder outByteRecord = new LongAdder();

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        outByteRecord.add(byteBuf.readableBytes());
        ctx.write(msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        // System.out.println("当前出口流量：" + outByteRecord.sum() + "字节！");
    }

}
