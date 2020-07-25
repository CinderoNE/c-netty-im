package com.cinder.im.client.console.list;

import com.cinder.im.client.console.AbstractConsoleCommand;
import com.cinder.im.protocol.packet.request.list.UserIdListRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description: 从服务器获取当前在线的所有用户Id
 * @Date create in 18:55 2020/7/22/022
 * @Modified By:
 */
public class UserIdListConsoleCommand extends AbstractConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        UserIdListRequestPacket userIdListRequestPacket = new UserIdListRequestPacket();
        channel.writeAndFlush(userIdListRequestPacket);
    }
}
