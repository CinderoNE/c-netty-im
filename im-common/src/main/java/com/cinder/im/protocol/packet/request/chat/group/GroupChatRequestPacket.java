package com.cinder.im.protocol.packet.request.chat.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:23 2020/7/23/023
 * @Modified By:
 */
@Data
public class GroupChatRequestPacket extends Packet {
    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.GROUP_CHAT_REQUEST;
    }
}
