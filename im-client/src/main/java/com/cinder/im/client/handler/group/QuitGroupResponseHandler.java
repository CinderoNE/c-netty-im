package com.cinder.im.client.handler.group;

import com.cinder.im.protocol.packet.response.group.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 23:20 2020/7/22/022
 * @Modified By:
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        String groupId = quitGroupResponsePacket.getGroupId();
        if(quitGroupResponsePacket.isSuccess()){
            System.out.println("退出群聊【" + quitGroupResponsePacket.getGroupId() + "】成功！");
        }else{
            System.out.println(quitGroupResponsePacket.getReason());
        }
    }
}
