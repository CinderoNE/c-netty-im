package com.cinder.im.server;

import com.cinder.im.protocol.codec.PacketCodecHandler;
import com.cinder.im.protocol.codec.Spliter;
import com.cinder.im.server.handler.ImServerIdleStateHandler;
import com.cinder.im.server.handler.*;
import com.cinder.im.server.handler.heartbeat.HeartBeatRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author Cinder
 * @Description:
 * @Date create in 1:53 2020/7/15/015
 * @Modified By:
 */
public class ImNettyServer {
    private static final int SERVER_PORT = 6670;
    public static void main(String[] args) {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        final ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(boosGroup, workerGroup)
                //设置IO类型为NIO
                .channel(NioServerSocketChannel.class)
                .attr(AttributeKey.newInstance("server-name"), "im-server")
                //ChannelOption.SO_KEEPALIVE 表示是否开启TCP底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                //ChannelOption.TCP_NODELAY 表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY,true)
                //表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //空闲检测
                        ch.pipeline().addLast(new ImServerIdleStateHandler());
                        //在线人数统计
                        ch.pipeline().addLast(CountUserHandler.INSTANCE);
                        //粘包、拆包
                        ch.pipeline().addLast(new Spliter());
                        //编码、解码
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        //客户端登录请求处理
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        //客户端心跳包处理
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        //权限处理
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        //各种packet处理
                        ch.pipeline().addLast(ImServerHandler.INSTANCE);
                    }
                });
        bind(serverBootstrap,SERVER_PORT);


    }

    /**
     * 端口被占用绑定下一个端口
     * @param serverBootstrap
     */
    private static void bind(ServerBootstrap serverBootstrap,int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("绑定端口[" + port + "]成功！");
                // serverBootstrap.config().group().scheduleAtFixedRate(() -> System.out.println("当前在线人数" + CountUserHandler.USER_COUNT)
                //         ,0,5, TimeUnit.SECONDS);
            } else {
                System.out.println("绑定端口[" + port + "]失败！");
                bind(serverBootstrap, port+1);
            }
        });
    }
}
