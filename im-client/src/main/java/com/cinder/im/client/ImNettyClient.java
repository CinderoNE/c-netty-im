package com.cinder.im.client;

import com.cinder.im.client.handler.ImClientIdleStateHandler;
import com.cinder.im.client.handler.heartbeat.HeartBeatSendHandler;
import com.cinder.im.client.handler.ImClientHandler;
import com.cinder.im.client.handler.flow.InFlowRecordHandler;
import com.cinder.im.client.handler.flow.OutFlowRecordHandler;
import com.cinder.im.client.util.ConnectUtil;
import com.cinder.im.protocol.codec.PacketCodecHandler;
import com.cinder.im.protocol.codec.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;


/**
 * @author Cinder
 * @Description:
 * @Date create in 2:10 2020/7/15/015
 * @Modified By:
 */
public class ImNettyClient {


    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .attr(AttributeKey.newInstance("client-name"), "im-client")
                //设置TCP参数
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //空闲检测
                        ch.pipeline().addLast(new ImClientIdleStateHandler());
                        //入口流量
                        ch.pipeline().addLast(new InFlowRecordHandler());
                        //出口流量
                        ch.pipeline().addLast(new OutFlowRecordHandler());
                        //解决粘包、拆包
                        ch.pipeline().addLast(new Spliter());
                        //解码、编码
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        //各种packet的handler
                        ch.pipeline().addLast(new ImClientHandler());
                        //定时心跳
                        ch.pipeline().addLast(new HeartBeatSendHandler());
                    }
                });
        ConnectUtil connectUtil = new ConnectUtil();
        connectUtil.setBootstrap(bootstrap);
        connectUtil.connect("localhost", 6670);
    }






}
