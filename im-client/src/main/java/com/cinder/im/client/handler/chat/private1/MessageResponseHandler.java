package com.cinder.im.client.handler.chat.private1;

import com.cinder.im.protocol.packet.response.chat.private1.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author Cinder
 * @Description:
 * @Date create in 22:27 2020/7/18/018
 * @Modified By:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    public static final String SERVER_ID = "1";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUsername = messageResponsePacket.getFromUsername();
        String msg = messageResponsePacket.getMsg();
        if(fromUserId.equals(SERVER_ID)){
            System.err.println("服务器消息：" + msg);
        }else {
            System.err.println("用户【" + fromUserId + ":" + fromUsername + "】-> " + msg);
        }
    }
}
