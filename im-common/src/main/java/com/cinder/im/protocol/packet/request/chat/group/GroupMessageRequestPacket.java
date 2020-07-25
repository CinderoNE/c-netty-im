package com.cinder.im.protocol.packet.request.chat.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:49 2020/7/23/023
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String msg;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
