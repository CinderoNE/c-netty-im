package com.cinder.im.protocol.packet.response;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:44 2020/7/23/023
 * @Modified By:
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
