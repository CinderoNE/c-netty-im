package com.cinder.im.server.handler.group;

import com.cinder.im.protocol.packet.request.group.CreateGroupRequestPacket;
import com.cinder.im.protocol.packet.response.group.CreateGroupResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.IdUtil;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author Cinder
 * @Description:
 * @Date create in 18:22 2020/7/22/022
 * @Modified By:
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    private CreateGroupRequestHandler(){}
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        Set<String> userIdSet = createGroupRequestPacket.getUserIdSet();
        //将群聊创建者加入userIdSet
        Session session = SessionUtil.getSession(ctx.channel());
        userIdSet.add(session.getUseId());

        //群聊channel组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        //群聊组用户名列表
        List<String> usernameList = new ArrayList<>(userIdSet.size());


        //根据id找出对应channel和username
        userIdSet.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                Session s = SessionUtil.getSession(channel);
                usernameList.add(s.getUsername());
            }
        });
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        //群聊只有一人（创建失败）
        if (usernameList.size() == 0){
            createGroupResponsePacket.setSuccess(false);
            createGroupResponsePacket.setReason("创建的群聊没有人！");
        }else {
            createGroupResponsePacket.setGroupId(IdUtil.randomId());
            createGroupResponsePacket.setSuccess(true);
            createGroupResponsePacket.setUserNameList(usernameList);
            SessionUtil.bindChannelGroup(createGroupResponsePacket.getGroupId(),channelGroup);
            System.out.print("【"+ usernameList.get(0) + "】创建群聊成功，群聊id为【"
                    +createGroupResponsePacket.getGroupId()+ "】,");
            System.out.println("群聊中有：" + createGroupResponsePacket.getUserNameList());
        }

        channelGroup.writeAndFlush(createGroupResponsePacket);


    }
}
