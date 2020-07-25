package com.cinder.im.protocol.packet.request.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

import java.util.Set;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:11 2020/7/22/022
 * @Modified By:
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private Set<String> userIdSet;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
