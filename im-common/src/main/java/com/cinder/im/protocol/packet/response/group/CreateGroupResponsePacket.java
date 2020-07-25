package com.cinder.im.protocol.packet.response.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.Data;

import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:38 2020/7/22/022
 * @Modified By:
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private List<String> userNameList;

    private boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
