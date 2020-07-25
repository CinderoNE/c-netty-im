package com.cinder.im.protocol.packet.request.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:52 2020/7/22/022
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinGroupRequestPacket extends Packet {

    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
