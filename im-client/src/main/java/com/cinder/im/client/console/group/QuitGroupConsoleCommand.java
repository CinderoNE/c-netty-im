package com.cinder.im.client.console.group;

import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.protocol.packet.request.group.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:48 2020/7/22/022
 * @Modified By:
 */
public class QuitGroupConsoleCommand extends AbstractConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入要退出的groupId");
        String groupId = scanner.nextLine();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
