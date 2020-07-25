package com.cinder.im.protocol.codec;

import com.cinder.im.protocol.command.Command;
import com.cinder.im.protocol.packet.Packet;
import com.cinder.im.protocol.packet.request.*;
import com.cinder.im.protocol.packet.request.chat.group.GroupMessageRequestPacket;
import com.cinder.im.protocol.packet.request.group.CreateGroupRequestPacket;
import com.cinder.im.protocol.packet.request.group.JoinGroupRequestPacket;
import com.cinder.im.protocol.packet.request.chat.group.GroupChatRequestPacket;
import com.cinder.im.protocol.packet.response.chat.group.GroupChatResponsePacket;
import com.cinder.im.protocol.packet.request.list.ListGroupMembersRequestPacket;
import com.cinder.im.protocol.packet.request.group.QuitGroupRequestPacket;
import com.cinder.im.protocol.packet.request.list.UserIdListRequestPacket;
import com.cinder.im.protocol.packet.request.chat.private1.MessageRequestPacket;
import com.cinder.im.protocol.packet.request.chat.private1.PrivateChatRequestPacket;
import com.cinder.im.protocol.packet.response.*;
import com.cinder.im.protocol.packet.response.chat.group.GroupMessageResponsePacket;
import com.cinder.im.protocol.packet.response.group.*;
import com.cinder.im.protocol.packet.response.list.ListGroupMembersResponsePacket;
import com.cinder.im.protocol.packet.response.list.UserIdListResponsePacket;
import com.cinder.im.protocol.packet.response.chat.private1.MessageResponsePacket;
import com.cinder.im.protocol.packet.response.chat.private1.PrivateChatResponsePacket;
import com.cinder.im.protocol.serializer.Serializer;
import com.cinder.im.protocol.serializer.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cinder
 * @Description: Packet解码、编码
 * @Date create in 4:01 2020/7/15/015
 * @Modified By:
 */
public class PacketCodec {

    /**
     *  自定义魔数
     */
    public static final int MAGIC_NUMBER = 0x11201120;
    /**
     * 单列
     */
    public static final PacketCodec INSTANCE = new PacketCodec();
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodec(){
        packetTypeMap = new HashMap<>();

        packetTypeMap.put(Command.LOGIN_REQUEST,LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(Command.PRIVATE_CHAT_REQUEST, PrivateChatRequestPacket.class);
        packetTypeMap.put(Command.PRIVATE_CHAT_RESPONSE, PrivateChatResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.USER_ID_LIST_REQUEST, UserIdListRequestPacket.class);
        packetTypeMap.put(Command.USER_ID_LIST_RESPONSE, UserIdListResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.OTHER_JOIN_OR_QUIT_GROUP_RESPONSE, OtherJoinOrQuitGroupResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(Command.GROUP_CHAT_REQUEST, GroupChatRequestPacket.class);
        packetTypeMap.put(Command.GROUP_CHAT_RESPONSE, GroupChatResponsePacket.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        packetTypeMap.put(Command.HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMap.put(Command.HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);


        serializerMap = new HashMap<>();
        Serializer jsonSerialize = new JSONSerializer();
        serializerMap.put(jsonSerialize.getSerializerAlgorithm(),jsonSerialize);

    }

    public ByteBuf encode(ByteBuf byteBuf,Packet packet){
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serializer(packet);
        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER)
                .writeByte(packet.getVersion())
                .writeByte(Serializer.DEFAULT.getSerializerAlgorithm())
                .writeByte(packet.getCommand())
                .writeInt(bytes.length)
                .writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        //跳过魔数
        byteBuf.skipBytes(4);
        //跳过协议版本
        byteBuf.skipBytes(1);
        //序列化算法
        byte serializerAlgorithm = byteBuf.readByte();
        //命令类型
        byte commandType = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();
        //数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        //反序列化
        Class<? extends Packet> packetType = packetTypeMap.get(commandType);
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        if(packetType != null && serializer != null){
            return serializer.deserialize(packetType, bytes);
        }
        return null;
    }
}
