package com.cinder.im.protocol.packet.response.chat.private1;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 23:25 2020/7/19/019
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateChatResponsePacket extends Packet {
    private boolean success;
    private String reason;
    private String toUserId;

    @Override
    public Byte getCommand() {
        return Command.PRIVATE_CHAT_RESPONSE;
    }
}
