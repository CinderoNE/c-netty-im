package com.cinder.im.server.handler.chat.private1;

import com.cinder.im.protocol.packet.request.chat.private1.MessageRequestPacket;
import com.cinder.im.protocol.packet.response.chat.private1.MessageResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author Cinder
 * @Description:
 * @Date create in 22:39 2020/7/18/018
 * @Modified By:
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    private MessageRequestHandler(){}
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        String toUserId = messageRequestPacket.getToUserId();

        //最终要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

        //获得接收方法channel
        Channel toChannel = SessionUtil.getChannel(toUserId);

        //发送消息给目标用户
        if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
            //目标在线

            //拿到发送方对应session
            Session session = SessionUtil.getSession(ctx.channel());

            messageResponsePacket.setFromUserId(session.getUseId());
            messageResponsePacket.setFromUsername(session.getUsername());
            messageResponsePacket.setMsg(messageRequestPacket.getMsg());
            toChannel.writeAndFlush(messageResponsePacket);
        }else {
            //目标不在线
            messageResponsePacket.setMsg("目标用户【" + toUserId + "】当前不在线");
            messageResponsePacket.setFromUsername("server");
            messageResponsePacket.setFromUserId("1");
            ctx.channel().writeAndFlush(messageResponsePacket);
        }

    }
}
