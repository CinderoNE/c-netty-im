package com.cinder.im.protocol.packet.response.chat.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:25 2020/7/23/023
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupChatResponsePacket extends Packet {
    private boolean success;
    private String reason;
    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.GROUP_CHAT_RESPONSE;
    }
}
