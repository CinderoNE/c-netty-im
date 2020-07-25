package com.cinder.im.client.handler.list;

import com.cinder.im.protocol.packet.response.list.UserIdListResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:08 2020/7/22/022
 * @Modified By:
 */
public class UserIdListResponseHandler extends SimpleChannelInboundHandler<UserIdListResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserIdListResponsePacket msg) throws Exception {
        System.out.println("当前在线userId：" + msg.getAllUserId());
    }
}
