package com.cinder.im.client.console;

import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:48 2020/7/19/019
 * @Modified By:
 */
public abstract class AbstractConsoleCommand {

    /**
     * 执行对应输入逻辑
     * @param scanner
     *
     */
    public abstract void exec (Scanner scanner, Channel channel);

    protected void writeAndFlush(Channel channel,Packet packet){
        channel.writeAndFlush(packet).addListener(future -> {
            if (!future.isSuccess()){
                System.out.println("请求失败，原因："+ future.cause());
                WaitUtil.responseFinished();
            }
        });
    }
}
