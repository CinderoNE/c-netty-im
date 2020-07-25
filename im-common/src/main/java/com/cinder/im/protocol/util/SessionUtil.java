package com.cinder.im.protocol.util;

import com.cinder.im.protocol.attribute.Attributes;
import com.cinder.im.protocol.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:58 2020/7/19/019
 * @Modified By:
 */
public class SessionUtil {

    private static final Map<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();

    private static final Map<String,ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();


    public static void bindSession(Session session,Channel channel){
        USER_ID_CHANNEL_MAP.put(session.getUseId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel){
        if(hasLogin(channel)){
            Session session = getSession(channel);
            USER_ID_CHANNEL_MAP.remove(session.getUseId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup){
        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static void unbindChannelGroup(String groupId){
        GROUP_ID_CHANNEL_GROUP_MAP.remove(groupId);
    }

    public static ChannelGroup getChannelGroup(String groupId){
        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Session> attr = channel.attr(Attributes.SESSION);
        return attr != null && attr.get() != null;
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId){
        return USER_ID_CHANNEL_MAP.get(userId);
    }



    public static List<String> getAllUerId(){
        return new ArrayList<>(USER_ID_CHANNEL_MAP.keySet());
    }
}
