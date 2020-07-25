package com.cinder.im.server.handler.list;

import com.cinder.im.protocol.packet.request.list.UserIdListRequestPacket;
import com.cinder.im.protocol.packet.response.list.UserIdListResponsePacket;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:59 2020/7/22/022
 * @Modified By:
 */
@ChannelHandler.Sharable
public class UserIdListRequestHandler extends SimpleChannelInboundHandler<UserIdListRequestPacket> {
    private UserIdListRequestHandler(){}
    public static final UserIdListRequestHandler INSTANCE = new UserIdListRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserIdListRequestPacket msg) throws Exception {
        List<String> allUerId = SessionUtil.getAllUerId();
        ctx.channel().writeAndFlush(new UserIdListResponsePacket(allUerId));
    }
}
