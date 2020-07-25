package com.cinder.im.client.console.list;

import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.protocol.packet.request.list.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 0:40 2020/7/23/023
 * @Modified By:
 */
public class ListGroupMembersConsoleCommand extends AbstractConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，查询群聊成员列表");
        String groupId = scanner.nextLine();
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
