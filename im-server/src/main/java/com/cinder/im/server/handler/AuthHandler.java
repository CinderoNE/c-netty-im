package com.cinder.im.server.handler;

import com.cinder.im.protocol.attribute.Attributes;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:03 2020/7/19/019
 * @Modified By:
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {
    private AuthHandler(){}
    public static final AuthHandler INSTANCE = new AuthHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(SessionUtil.hasLogin(ctx.channel())){
            //已登录，删除handle避免重复验证
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }else {
            //未登录，直接关闭连接。
            //生产环境可以添加提示登录等业务逻辑
            ctx.channel().close();
        }

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.hasLogin(ctx.channel())){
            System.out.println("已验证登录，移除handler");
        }
    }
}
