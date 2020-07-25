package com.cinder.im.protocol.packet.response.group;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.protocol.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:21 2020/7/22/022
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherJoinOrQuitGroupResponsePacket extends Packet {
    /**
     * 加入群聊的用户信息
     */
    private Session session;
    private String groupId;
    private boolean join;
    @Override
    public Byte getCommand() {
        return Command.OTHER_JOIN_OR_QUIT_GROUP_RESPONSE;
    }
}
