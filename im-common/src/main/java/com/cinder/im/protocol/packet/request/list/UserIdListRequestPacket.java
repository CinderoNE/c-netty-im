package com.cinder.im.protocol.packet.request.list;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:56 2020/7/22/022
 * @Modified By:
 */
public class UserIdListRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.USER_ID_LIST_REQUEST;
    }
}
