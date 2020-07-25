package com.cinder.im.protocol.attribute;

import com.cinder.im.protocol.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:43 2020/7/18/018
 * @Modified By:
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
