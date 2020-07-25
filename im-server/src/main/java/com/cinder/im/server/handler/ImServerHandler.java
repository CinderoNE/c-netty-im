package com.cinder.im.server.handler;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.server.handler.chat.group.GroupChatRequestHandler;
import com.cinder.im.server.handler.chat.group.GroupMessageRequestHandler;
import com.cinder.im.server.handler.chat.private1.MessageRequestHandler;
import com.cinder.im.server.handler.chat.private1.PrivateChatRequestHandler;
import com.cinder.im.server.handler.group.CreateGroupRequestHandler;
import com.cinder.im.server.handler.group.JoinGroupRequestHandler;
import com.cinder.im.server.handler.group.QuitGroupRequestHandler;
import com.cinder.im.server.handler.list.ListGroupMembersRequestHandler;
import com.cinder.im.server.handler.list.UserIdListRequestHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:58 2020/7/23/023
 * @Modified By:
 */
@ChannelHandler.Sharable
public class ImServerHandler extends SimpleChannelInboundHandler<Packet> {
    private Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap;
    private ImServerHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(Command.PRIVATE_CHAT_REQUEST, PrivateChatRequestHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_CHAT_REQUEST, GroupChatRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(Command.USER_ID_LIST_REQUEST, UserIdListRequestHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
    }
    public static final ImServerHandler INSTANCE = new ImServerHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
