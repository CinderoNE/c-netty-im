package com.cinder.im.client.handler.group;

import com.cinder.im.protocol.packet.response.group.OtherJoinOrQuitGroupResponsePacket;
import com.cinder.im.protocol.session.Session;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:30 2020/7/22/022
 * @Modified By:
 */
public class OtherJoinOrQuitGroupResponseHandler extends SimpleChannelInboundHandler<OtherJoinOrQuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OtherJoinOrQuitGroupResponsePacket msg) throws Exception {
        Session session = msg.getSession();
        String groupId = msg.getGroupId();
        if (msg.isJoin()) {
            System.out.println("【" + session.getUseId() + ":" + session.getUsername()
                    + "】加入群聊【" + groupId + "】");
        }else {
            System.out.println("【" + session.getUseId() + ":" + session.getUsername()
                    + "】退出群聊【" + groupId + "】");
        }
    }
}
