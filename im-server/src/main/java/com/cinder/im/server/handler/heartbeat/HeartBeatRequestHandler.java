package com.cinder.im.server.handler.heartbeat;

import com.cinder.im.protocol.attribute.Attributes;
import com.cinder.im.protocol.packet.request.HeartBeatRequestPacket;
import com.cinder.im.protocol.packet.response.HeartBeatResponsePacket;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:47 2020/7/23/023
 * @Modified By:
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    private HeartBeatRequestHandler() {}
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
