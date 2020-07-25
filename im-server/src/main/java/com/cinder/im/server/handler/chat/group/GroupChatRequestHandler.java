package com.cinder.im.server.handler.chat.group;

import com.cinder.im.protocol.packet.request.chat.group.GroupChatRequestPacket;
import com.cinder.im.protocol.packet.response.chat.group.GroupChatResponsePacket;
import com.cinder.im.protocol.util.SessionUtil;
import com.cinder.im.protocol.util.ThreadPoolUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.concurrent.Future;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:37 2020/7/23/023
 * @Modified By:
 */
@ChannelHandler.Sharable
public class GroupChatRequestHandler extends SimpleChannelInboundHandler<GroupChatRequestPacket> {

    private GroupChatRequestHandler(){
    }

    public static final GroupChatRequestHandler INSTANCE = new GroupChatRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestPacket groupChatRequestPacket) throws Exception {
        /**
         * 单个 NIO 线程执行的抽象逻辑
         * List<Channel> channelList = 已有数据可读的 channel
         * for (Channel channel in channelList) {
         *    for (ChannelHandler handler in channel.pipeline()) {
         *        handler.channelRead0();  //同步调用
         *    }
         * }
         * 将耗费时间长的业务丢到业务线程池中去处理，避免阻塞NIO线程
         * Netty 会判断当前调用的是哪个线程，如果是 NIO 线程本身，那么直接是同步调用的
         */
        ThreadPoolUtil.execute(() -> {
            String groupId = groupChatRequestPacket.getGroupId();
            ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
            GroupChatResponsePacket groupChatResponsePacket = new GroupChatResponsePacket();
            groupChatResponsePacket.setGroupId(groupId);
            if (channelGroup != null && channelGroup.contains(ctx.channel())){
                //对应channelGroup存在，且当前用户在群聊组中
                groupChatResponsePacket.setSuccess(true);
            }else {
                groupChatResponsePacket.setSuccess(false);
                if (channelGroup == null){
                    groupChatResponsePacket.setReason("对应群聊组不存在");
                }else {
                    groupChatResponsePacket.setReason("你未加入当前群聊组，请先加入！");
                }
            }
            ctx.channel().writeAndFlush(groupChatResponsePacket);
        });
    }
}
