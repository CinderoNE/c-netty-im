package com.cinder.im.protocol.serializer;

import com.cinder.im.protocol.serializer.impl.JSONSerializer;

/**
 * @author Cinder
 * @Description:序列化接口
 * @Date create in 3:46 2020/7/15/015
 * @Modified By:
 */
public interface Serializer {


    Serializer DEFAULT = new JSONSerializer();

    /**
     * 获取序列化算法
     * @return
     */
    Byte getSerializerAlgorithm();

    /**
     * Java对象转换为字节数组
     * @param object 要转换的对象
     * @return
     */
    byte[] serializer(Object object);

    /**
     * 字节数组转换位Java对象
     * @param clazz 目标Class
     * @param bytes 字节数组
     * @param <T> 目标类型
     * @return
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);
}
