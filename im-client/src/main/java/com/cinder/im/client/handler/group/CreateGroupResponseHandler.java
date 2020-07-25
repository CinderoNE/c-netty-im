package com.cinder.im.client.handler.group;

import com.cinder.im.protocol.packet.response.group.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:47 2020/7/22/022
 * @Modified By:
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
       if (createGroupResponsePacket.isSuccess()){
           List<String> userNameList = createGroupResponsePacket.getUserNameList();
           System.out.println("【"+ userNameList.get(0) + "】创建群聊成功，群聊id为【"
                   +createGroupResponsePacket.getGroupId()+ "】,");
           System.out.println("群聊中有：" + createGroupResponsePacket.getUserNameList());
       }else{
           System.err.println("【创建失败】，原因：" + createGroupResponsePacket.getReason());
       }
    }
}
