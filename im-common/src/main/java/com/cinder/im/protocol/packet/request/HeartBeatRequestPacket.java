package com.cinder.im.protocol.packet.request;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:43 2020/7/23/023
 * @Modified By:
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
