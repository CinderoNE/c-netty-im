package com.cinder.im.protocol.packet.response.chat.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.protocol.session.Session;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:59 2020/7/23/023
 * @Modified By:
 */
@Data
public class GroupMessageResponsePacket extends Packet {
    private String msg;

    private String groupId;

    private Session session;
    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
