package com.cinder.im.server.handler.chat.private1;

import com.cinder.im.protocol.packet.request.chat.private1.PrivateChatRequestPacket;
import com.cinder.im.protocol.packet.response.chat.private1.PrivateChatResponsePacket;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 2:40 2020/7/20/020
 * @Modified By:
 */
@ChannelHandler.Sharable
public class PrivateChatRequestHandler extends SimpleChannelInboundHandler<PrivateChatRequestPacket> {
    private PrivateChatRequestHandler(){}
    public static final PrivateChatRequestHandler INSTANCE = new PrivateChatRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PrivateChatRequestPacket privateChatRequestPacket) throws Exception {
        String toUserId = privateChatRequestPacket.getToUserId();
        Channel toChannel = SessionUtil.getChannel(toUserId);
        PrivateChatResponsePacket privateChatResponsePacket = new PrivateChatResponsePacket();

        if (toChannel == null || !SessionUtil.hasLogin(toChannel)){
            //目标用户不存在或离线
            privateChatResponsePacket.setSuccess(false);
            privateChatResponsePacket.setReason("目标用户不存在或离线！");
        }else {
            //目标用户在线
            privateChatResponsePacket.setSuccess(true);
            privateChatResponsePacket.setToUserId(toUserId);
        }
        ctx.channel().writeAndFlush(privateChatResponsePacket);
    }
}
