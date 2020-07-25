package com.cinder.im.client.handler.list;

import com.cinder.im.protocol.packet.response.list.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author Cinder
 * @Description:
 * @Date create in 1:08 2020/7/23/023
 * @Modified By:
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        if(listGroupMembersResponsePacket.isSuccess()){
            System.out.println("群聊组【" + listGroupMembersResponsePacket.getGroupId()
                    + "】成员包括" + listGroupMembersResponsePacket.getSessionList());
        }else {
            System.out.println(listGroupMembersResponsePacket.getReason());
        }
    }
}
