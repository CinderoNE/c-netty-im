package com.cinder.im.protocol.packet.request.list;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 0:40 2020/7/23/023
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
