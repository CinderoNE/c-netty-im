package com.cinder.im.protocol.packet.response.list;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:06 2020/7/22/022
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdListResponsePacket extends Packet {
    private List<String> allUserId;

    @Override
    public Byte getCommand() {
        return Command.USER_ID_LIST_RESPONSE;
    }
}
