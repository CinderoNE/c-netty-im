package com.cinder.im.protocol.packet.response.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:10 2020/7/22/022
 * @Modified By:
 */
@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
