package com.cinder.im.client.handler.chat.group;

import com.cinder.im.client.console.ConsoleCommandManager;
import com.cinder.im.client.status.InputStatus;
import com.cinder.im.protocol.packet.response.chat.group.GroupChatResponsePacket;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:42 2020/7/23/023
 * @Modified By:
 */
public class GroupChatResponseHandler extends SimpleChannelInboundHandler<GroupChatResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatResponsePacket groupChatResponsePacket) throws Exception {
        String groupId = groupChatResponsePacket.getGroupId();
        if (groupChatResponsePacket.isSuccess()) {
            //对应群组存在
            ConsoleCommandManager.inputStatus = InputStatus.GROUP;
            ConsoleCommandManager.inputStatus.setId(groupId);
        }else{
            System.out.println(groupChatResponsePacket.getReason());
        }
        WaitUtil.responseFinished();
    }
}
