package com.cinder.im.protocol.packet.response.list;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.protocol.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 0:54 2020/7/23/023
 * @Modified By:
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
