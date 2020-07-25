package com.cinder.im.server.handler.chat.group;

import com.cinder.im.protocol.packet.request.chat.group.GroupMessageRequestPacket;
import com.cinder.im.protocol.packet.response.chat.group.GroupMessageResponsePacket;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:52 2020/7/23/023
 * @Modified By:
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    private GroupMessageRequestHandler(){
    }

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        System.out.println("groupMessageRequestPacket = " + groupMessageRequestPacket);
        String groupId = groupMessageRequestPacket.getGroupId();
        String msg = groupMessageRequestPacket.getMsg();
        //获得channelGroup
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setGroupId(groupId);
        groupMessageResponsePacket.setMsg(msg);
        //设置发送消息的用户信息
        groupMessageResponsePacket.setSession(SessionUtil.getSession(ctx.channel()));
        //将消息发送给群聊组中的用户
        channelGroup.forEach(channel -> {
            if(channel != ctx.channel()){
                channel.writeAndFlush(groupMessageResponsePacket);
            }
        });



    }
}
