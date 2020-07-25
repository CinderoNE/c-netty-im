package com.cinder.im.server.handler.group;

import com.cinder.im.protocol.packet.request.group.QuitGroupRequestPacket;
import com.cinder.im.protocol.packet.response.group.OtherJoinOrQuitGroupResponsePacket;
import com.cinder.im.protocol.packet.response.group.QuitGroupResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:51 2020/7/22/022
 * @Modified By:
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    private QuitGroupRequestHandler(){}
    public static QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        //获取对应channelGroup，移除当前channel
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);

        if (channelGroup != null) {
            //对应group存在
            channelGroup.remove(ctx.channel());
            quitGroupResponsePacket.setSuccess(true);
            quitGroupResponsePacket.setGroupId(groupId);
            if (channelGroup.isEmpty()){
                //群聊人数为零
                SessionUtil.unbindChannelGroup(groupId);
                channelGroup.close();
                System.out.println("解散群聊【" + groupId + "】！");
            }
            //通知其他人
            Session session = SessionUtil.getSession(ctx.channel());
            channelGroup.writeAndFlush(new OtherJoinOrQuitGroupResponsePacket(session,groupId,false));
        }else {
            quitGroupResponsePacket.setSuccess(false);
            quitGroupResponsePacket.setReason("对应group不存在！");
        }
        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
    }
}
