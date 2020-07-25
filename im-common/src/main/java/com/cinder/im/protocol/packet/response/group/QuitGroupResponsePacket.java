package com.cinder.im.protocol.packet.response.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:53 2020/7/22/022
 * @Modified By:
 */
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;
    private String reason;
    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
