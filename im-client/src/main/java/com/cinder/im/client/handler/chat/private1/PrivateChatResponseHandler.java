package com.cinder.im.client.handler.chat.private1;

import com.cinder.im.client.console.ConsoleCommandManager;
import com.cinder.im.client.status.InputStatus;
import com.cinder.im.protocol.packet.response.chat.private1.PrivateChatResponsePacket;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 23:29 2020/7/19/019
 * @Modified By:
 */
public class PrivateChatResponseHandler extends SimpleChannelInboundHandler<PrivateChatResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PrivateChatResponsePacket privateChatResponsePacket) throws Exception {
        String toUserId = privateChatResponsePacket.getToUserId();
        if (privateChatResponsePacket.isSuccess()) {
            //私聊目标在线
            ConsoleCommandManager.inputStatus = InputStatus.PRIVATE;
            ConsoleCommandManager.inputStatus.setId(toUserId);
        }else {
            System.out.println(privateChatResponsePacket.getReason());
        }
        WaitUtil.responseFinished();
    }

}
