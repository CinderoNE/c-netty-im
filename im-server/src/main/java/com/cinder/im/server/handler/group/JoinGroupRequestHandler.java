package com.cinder.im.server.handler.group;

import com.cinder.im.protocol.packet.request.group.JoinGroupRequestPacket;
import com.cinder.im.protocol.packet.response.group.JoinGroupResponsePacket;
import com.cinder.im.protocol.packet.response.group.OtherJoinOrQuitGroupResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:57 2020/7/22/022
 * @Modified By:
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    private JoinGroupRequestHandler(){}
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        if (channelGroup != null) {
            //group存在
            channelGroup.add(ctx.channel());
            joinGroupResponsePacket.setSuccess(true);
            //通知已在群聊的用户
            Session session = SessionUtil.getSession(ctx.channel());
            OtherJoinOrQuitGroupResponsePacket otherJoinOrQuitGroupResponsePacket = new OtherJoinOrQuitGroupResponsePacket(session, groupId, true);
            channelGroup.forEach(channel -> {
                //排除自己
                if (channel != ctx.channel()){
                    channel.writeAndFlush(otherJoinOrQuitGroupResponsePacket);
                }
            });
        }else{
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setReason("groupId不存在！");
        }
        ctx.channel().writeAndFlush(joinGroupResponsePacket);



    }
}
