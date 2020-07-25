package com.cinder.im.protocol.packet.request.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:47 2020/7/22/022
 * @Modified By:
 */
@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
