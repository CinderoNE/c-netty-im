package com.cinder.im.server.handler;

import com.cinder.im.protocol.attribute.Attributes;
import com.cinder.im.protocol.codec.PacketCodec;
import com.cinder.im.protocol.packet.request.LoginRequestPacket;
import com.cinder.im.protocol.packet.response.LoginResponsePacket;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.IdUtil;
import com.cinder.im.protocol.util.SessionUtil;
import com.cinder.im.protocol.util.TimeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author Cinder
 * @Description: 处理客户端的登录请求
 * @Date create in 18:04 2020/7/16/016
 * @Modified By:
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private LoginRequestHandler(){
    }
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if(valid(loginRequestPacket)){
            //登录成功
            loginResponsePacket.setSuccess(true);
            String userId = IdUtil.randomId();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUsername(loginRequestPacket.getUsername());
            System.out.println("【" + ctx.channel().remoteAddress() + "】登录成功！");
            SessionUtil.bindSession(new Session(userId,loginRequestPacket.getUsername()), ctx.channel());
        }else{
            //登录失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名或密码错误！");
        }
        //返回登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        System.out.println(TimeUtil.now() + "【" + loginRequestPacket.getUsername() + "】请求登录");
        String username = loginRequestPacket.getUsername();
        String password = loginRequestPacket.getPassword();
        return (username + "123").equals(password);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());

        if (session != null) {
            String username = session.getUsername();
            SessionUtil.unbindSession(ctx.channel());
            System.out.println("【" + username + "】客户端断开连接");
        }

        super.channelInactive(ctx);
    }




}
