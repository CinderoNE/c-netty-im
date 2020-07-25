package com.cinder.im.client.handler;

import com.cinder.im.protocol.packet.response.LoginResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.WaitUtil;
import com.cinder.im.protocol.util.SessionUtil;
import com.cinder.im.protocol.util.TimeUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;



/**
 * @author Cinder
 * @Description: 处理服务器对登录请求的响应
 * @Date create in 17:48 2020/7/16/016
 * @Modified By:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUsername();
        if (loginResponsePacket.isSuccess()){
            System.out.println(TimeUtil.now() + "：【" + userName + "】登录成功！随机userId：" + userId);
            SessionUtil.bindSession(new Session(userId,userName),ctx.channel());
        }else{
            System.out.println(TimeUtil.now() + "：登录失败，原因：" + loginResponsePacket.getReason());
        }
        //登录响应处理完成
        WaitUtil.responseFinished();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("客户端连接被关闭");
        super.channelInactive(ctx);
    }
}
