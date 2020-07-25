package com.cinder.im.protocol.packet.request.chat.private1;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:57 2020/7/18/018
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String msg;

    private String toUserId;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
