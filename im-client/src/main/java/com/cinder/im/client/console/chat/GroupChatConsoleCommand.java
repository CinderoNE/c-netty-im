package com.cinder.im.client.console.chat;

import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.client.console.ConsoleCommandManager;
import com.cinder.im.client.status.InputStatus;
import com.cinder.im.protocol.packet.request.chat.group.GroupChatRequestPacket;
import com.cinder.im.protocol.packet.request.chat.group.GroupMessageRequestPacket;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 2:58 2020/7/23/023
 * @Modified By:
 */
public class GroupChatConsoleCommand extends AbstractConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入群聊组ID");
        String groupId = scanner.nextLine();
        GroupChatRequestPacket groupChatRequestPacket = new GroupChatRequestPacket();
        groupChatRequestPacket.setGroupId(groupId);
        writeAndFlush(channel, groupChatRequestPacket);
        WaitUtil.waitResponse();
        if(ConsoleCommandManager.inputStatus.equals(InputStatus.GROUP)){
            startGroupChat(scanner,channel);
        }
    }

    private void startGroupChat(Scanner scanner,Channel channel) {
        String groupId = ConsoleCommandManager.inputStatus.getId();
        System.out.println("-------" + ConsoleCommandManager.inputStatus.getDescription() + "-------");
        while (!Thread.interrupted()) {
            System.out.println("发送消息给群聊组【" + groupId + "】");
            String msg = scanner.nextLine();
            if(msg.equals(ConsoleCommandManager.QUIT)){
                //退出，返回命令模式
                System.out.println("-------退出"+ConsoleCommandManager.inputStatus.getDescription()+"-------");
                ConsoleCommandManager.inputStatus = InputStatus.COMMAND;
                return;
            }
            channel.writeAndFlush(new GroupMessageRequestPacket(msg,groupId));
        }
    }
}
