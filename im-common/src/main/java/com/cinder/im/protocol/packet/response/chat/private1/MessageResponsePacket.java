package com.cinder.im.protocol.packet.response.chat.private1;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:01 2020/7/18/018
 * @Modified By:
 */
@Data
public class MessageResponsePacket extends Packet {
    private String msg;

    private String fromUserId;

    private String fromUsername;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
