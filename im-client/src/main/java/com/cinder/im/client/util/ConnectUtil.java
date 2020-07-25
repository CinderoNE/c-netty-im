package com.cinder.im.client.util;

import com.cinder.im.client.console.ConsoleCommandManager;
import com.cinder.im.client.console.LoginConsoleCommand;
import com.cinder.im.protocol.util.SessionUtil;
import com.cinder.im.protocol.util.ThreadPoolUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:13 2020/7/23/023
 * @Modified By:
 */
@Data
public class ConnectUtil {

    private static final int DEFAULT_RETRY = 5;
    /**
     * 记录当前重试次数
     */
    private static int r = 0;


    private Bootstrap bootstrap;


    public void connect(String host, int port){
        connect(host,port,DEFAULT_RETRY);
    }
    /**
     * 建立与服务器连接，失败会进行重试
     * @param host 服务器ip或域名
     * @param port 服务器端口
     * @param retry 重试次数
     */
    public void connect(String host, int port, int retry){
        if (bootstrap == null){
            throw new RuntimeException("bootstrap not set!");
        }
        synchronized (bootstrap) {
            bootstrap.connect(host, port).addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("连接" + host + port + "成功，开始启动输入线程");
                    Channel channel = ((ChannelFuture)future) .channel();
                    startInput(channel);
                    r = 0;
                }else if (retry == 0){
                    r = 0;
                    System.out.println("重试结束！");
                }else{
                    //第几次重试
                    r++;
                    //重连时间间隔
                    int delay = 1 << r;
                    System.err.println(LocalDateTime.now() + "：连接失败，原因：" + future.cause()
                            + delay +"秒后尝试第" + r + "次重连");
                    bootstrap.config().group().schedule(
                            () -> connect(host, port, retry-1), delay, TimeUnit.SECONDS);
                }
            });
        }
    }

    /**
     * 开启输入线程
     *
     */
    private void startInput(Channel channel){
        Scanner scanner = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager(channel,scanner);
        ThreadPoolUtil.execute(() -> {
            while (!Thread.interrupted()) {
                while (channel.isActive()){
                    if (!SessionUtil.hasLogin(channel)) {
                        //还未登录
                        loginConsoleCommand.exec(scanner,channel);
                    } else {
                        consoleCommandManager.exec();
                    }
                }
                System.err.println("通道关闭，输入线程停止");
                stopClient();
                break;
            }
        });
    }

    private void stopClient(){
        if (bootstrap != null) {
            System.out.println("关闭客户端");
            bootstrap.config().group().shutdownGracefully().addListener(future -> {
                if (!future.isSuccess()) {
                    System.out.println("future.cause() = " + future.cause());
                }else {
                    System.out.println("关闭线程池");
                    ThreadPoolUtil.shutdown();
                }
            });
        }
    }




}
