package com.cinder.im.protocol.packet.request;

import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.protocol.command.Command;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:42 2020/7/15/015
 * @Modified By:
 */
@Data
public class LoginRequestPacket extends Packet {


    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
