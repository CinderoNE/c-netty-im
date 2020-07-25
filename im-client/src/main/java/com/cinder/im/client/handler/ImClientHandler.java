package com.cinder.im.client.handler;

import com.cinder.im.client.handler.chat.group.GroupChatResponseHandler;
import com.cinder.im.client.handler.chat.group.GroupMessageResponseHandler;
import com.cinder.im.client.handler.chat.private1.MessageResponseHandler;
import com.cinder.im.client.handler.chat.private1.PrivateChatResponseHandler;
import com.cinder.im.client.handler.group.CreateGroupResponseHandler;
import com.cinder.im.client.handler.group.JoinGroupResponseHandler;
import com.cinder.im.client.handler.group.OtherJoinOrQuitGroupResponseHandler;
import com.cinder.im.client.handler.group.QuitGroupResponseHandler;
import com.cinder.im.client.handler.list.ListGroupMembersResponseHandler;
import com.cinder.im.client.handler.list.UserIdListResponseHandler;
import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinder
 * @Description:
 * @Date create in 17:43 2020/7/23/023
 * @Modified By:
 */
public class ImClientHandler extends SimpleChannelInboundHandler<Packet> {
    private Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap;
    public ImClientHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(Command.PRIVATE_CHAT_RESPONSE, new PrivateChatResponseHandler());
        handlerMap.put(Command.MESSAGE_RESPONSE, new MessageResponseHandler());
        handlerMap.put(Command.CREATE_GROUP_RESPONSE, new CreateGroupResponseHandler());
        handlerMap.put(Command.JOIN_GROUP_RESPONSE, new JoinGroupResponseHandler());
        handlerMap.put(Command.OTHER_JOIN_OR_QUIT_GROUP_RESPONSE, new OtherJoinOrQuitGroupResponseHandler());
        handlerMap.put(Command.QUIT_GROUP_RESPONSE, new QuitGroupResponseHandler());
        handlerMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, new ListGroupMembersResponseHandler());
        handlerMap.put(Command.USER_ID_LIST_RESPONSE, new UserIdListResponseHandler());
        handlerMap.put(Command.LOGIN_RESPONSE, new LoginResponseHandler());
        handlerMap.put(Command.GROUP_CHAT_RESPONSE,new GroupChatResponseHandler());
        handlerMap.put(Command.GROUP_MESSAGE_RESPONSE,new GroupMessageResponseHandler());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        SimpleChannelInboundHandler<? extends Packet> simpleChannelInboundHandler = handlerMap.get(packet.getCommand());
        if (simpleChannelInboundHandler != null) {
            simpleChannelInboundHandler.channelRead(ctx, packet);
        }
    }
}
