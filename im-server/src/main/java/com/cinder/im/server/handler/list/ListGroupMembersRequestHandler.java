package com.cinder.im.server.handler.list;

import com.cinder.im.protocol.packet.request.list.ListGroupMembersRequestPacket;
import com.cinder.im.protocol.packet.response.list.ListGroupMembersResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 0:45 2020/7/23/023
 * @Modified By:
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    private ListGroupMembersRequestHandler(){}
    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();

        //查找对应channelGroup
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        if (channelGroup != null) {
            //对应channelGroup为空，清除
            if (channelGroup.isEmpty()){
                SessionUtil.unbindChannelGroup(groupId);
                channelGroup.close();
                listGroupMembersResponsePacket.setSuccess(false);
                listGroupMembersResponsePacket.setReason("对应group为空");
            }else {
                //对应channelGroup存在且不为空
                List<Session> sessionList = new ArrayList<>();
                channelGroup.forEach(channel -> {
                    sessionList.add(SessionUtil.getSession(channel));
                });
                listGroupMembersResponsePacket.setSessionList(sessionList);
                listGroupMembersResponsePacket.setSuccess(true);
            }
        }else{
            listGroupMembersResponsePacket.setSuccess(false);
            listGroupMembersResponsePacket.setReason("对应group不存在");
        }
        ctx.channel().writeAndFlush(listGroupMembersResponsePacket);


    }
}
