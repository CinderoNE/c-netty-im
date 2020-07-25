package com.cinder.im.protocol.command;

/**
 * @author Cinder
 * @Description: 指令
 * @Date create in 3:34 2020/7/15/015
 * @Modified By:
 */
public interface Command {
    /**
     * 登录请求
     */
    byte LOGIN_REQUEST = 1;
    /**
     * 登录响应
     */
    byte LOGIN_RESPONSE = 2;

    /**
     *消息请求
     */
    byte MESSAGE_REQUEST = 3;
    /**
     *消息响应
     */
    byte MESSAGE_RESPONSE = 4;

    byte PRIVATE_CHAT_REQUEST = 5;

    byte PRIVATE_CHAT_RESPONSE = 6;

    byte CREATE_GROUP_REQUEST = 7;

    byte CREATE_GROUP_RESPONSE = 8;

    byte USER_ID_LIST_REQUEST = 9;

    byte USER_ID_LIST_RESPONSE = 10;

    byte JOIN_GROUP_REQUEST = 11;

    byte JOIN_GROUP_RESPONSE = 12;

    byte OTHER_JOIN_OR_QUIT_GROUP_RESPONSE = 13;

    byte QUIT_GROUP_REQUEST = 14;

    byte QUIT_GROUP_RESPONSE = 15;

    byte LIST_GROUP_MEMBERS_REQUEST = 16;

    byte LIST_GROUP_MEMBERS_RESPONSE = 17;

    byte GROUP_CHAT_REQUEST = 18;

    byte GROUP_CHAT_RESPONSE = 19;

    byte GROUP_MESSAGE_REQUEST = 20;

    byte GROUP_MESSAGE_RESPONSE = 21;

    byte HEARTBEAT_REQUEST = 22;

    byte HEARTBEAT_RESPONSE = 23;
}
