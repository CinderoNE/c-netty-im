package com.cinder.im.client.console.chat;


import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.client.console.ConsoleCommandManager;
import com.cinder.im.client.status.InputStatus;
import com.cinder.im.protocol.packet.request.chat.private1.MessageRequestPacket;
import com.cinder.im.protocol.packet.request.chat.private1.PrivateChatRequestPacket;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:58 2020/7/19/019
 * @Modified By:
 */
public class PrivateChatConsoleCommand extends AbstractConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入私聊用户ID");
        String toUserId = scanner.nextLine();
        PrivateChatRequestPacket privateChatRequestPacket = new PrivateChatRequestPacket(toUserId);
        writeAndFlush(channel,privateChatRequestPacket);
        WaitUtil.waitResponse();
        if (ConsoleCommandManager.inputStatus.equals(InputStatus.PRIVATE)) {
            startPrivateChat(scanner,channel);
        }
    }

    private void startPrivateChat(Scanner scanner,Channel channel) {
        String toUserId = ConsoleCommandManager.inputStatus.getId();
        System.out.println("-------" + ConsoleCommandManager.inputStatus.getDescription() + "-------");
        while (!Thread.interrupted()) {
            System.out.println("发送消息给【" + toUserId + "】");
            String msg = scanner.nextLine();
            if(msg.equals(ConsoleCommandManager.QUIT)){
                //退出，返回命令模式
                System.out.println("-------退出"+ConsoleCommandManager.inputStatus.getDescription()+"-------");
                ConsoleCommandManager.inputStatus = InputStatus.COMMAND;
                return;
            }
            channel.writeAndFlush(new MessageRequestPacket(msg,toUserId));
        }
    }

}
