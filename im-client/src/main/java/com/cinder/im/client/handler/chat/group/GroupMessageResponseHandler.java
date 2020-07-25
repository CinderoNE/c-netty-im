package com.cinder.im.client.handler.chat.group;

import com.cinder.im.protocol.packet.response.chat.group.GroupMessageResponsePacket;
import com.cinder.im.protocol.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 4:13 2020/7/23/023
 * @Modified By:
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        Session session = groupMessageResponsePacket.getSession();
        String groupId = groupMessageResponsePacket.getGroupId();
        String msg = groupMessageResponsePacket.getMsg();
        System.err.println("群聊组【" + groupId + "】中【"
                + session.getUseId() + ":" + session.getUsername() + "】发来消息：" + msg);
    }
}
