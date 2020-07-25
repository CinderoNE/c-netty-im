package com.cinder.im.client.console;

import com.cinder.im.protocol.packet.request.LoginRequestPacket;
import com.cinder.im.protocol.util.WaitUtil;
import io.netty.channel.Channel;
import lombok.NoArgsConstructor;

import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:52 2020/7/19/019
 * @Modified By:
 */
@NoArgsConstructor
public class LoginConsoleCommand extends AbstractConsoleCommand {


    @Override
    public void exec(Scanner scanner,Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.println("请输入用户名：");
        String userName = scanner.nextLine();
        loginRequestPacket.setUsername(userName);
        System.out.println("请输入密码：");
        String pwd = scanner.nextLine();
        loginRequestPacket.setPassword(pwd);
        writeAndFlush(channel, loginRequestPacket);
        //等待处理登录响应
        WaitUtil.waitResponse();

    }
}
