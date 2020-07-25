package com.cinder.im.client.console.group;

import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.protocol.packet.request.group.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:51 2020/7/22/022
 * @Modified By:
 */
public class JoinGroupConsoleCommand extends AbstractConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入群聊groupId：");
        String groupId = scanner.nextLine();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
