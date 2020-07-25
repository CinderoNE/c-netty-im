package com.cinder.im.protocol.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.cinder.im.protocol.serializer.Serializer;
import com.cinder.im.protocol.serializer.SerializerAlgorithm;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:54 2020/7/15/015
 * @Modified By:
 */
public class JSONSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
