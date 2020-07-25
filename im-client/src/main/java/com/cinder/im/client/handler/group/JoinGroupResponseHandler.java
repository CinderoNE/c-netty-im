package com.cinder.im.client.handler.group;

import com.cinder.im.protocol.packet.response.group.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:14 2020/7/22/022
 * @Modified By:
 */

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            System.out.println("加入群聊【" + joinGroupResponsePacket.getGroupId() + "】成功！");
        }else {
            System.out.println(joinGroupResponsePacket.getReason());
        }
    }
}
