package com.cinder.im.client.console.group;

import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.protocol.packet.request.group.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.*;

/**
 * @author Cinder
 * @Description: 创建群聊命令
 * @Date create in 4:07 2020/7/20/020
 * @Modified By:
 */
public class CreatGroupConsoleCommand extends AbstractConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.println("【创建群聊】输入要邀请的userId，以英文逗号隔开！");
        String userIds = scanner.nextLine();
        String[] userId = userIds.split(",");
        Set<String> userIdSet = new HashSet<>(Arrays.asList(userId));
        createGroupRequestPacket.setUserIdSet(userIdSet);
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
