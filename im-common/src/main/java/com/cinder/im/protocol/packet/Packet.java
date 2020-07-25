package com.cinder.im.protocol.packet;

import com.alibaba.fastjson.annotation.JSONField;
import com.cinder.im.protocol.command.Command;
import lombok.Data;

/**
 * @author Cinder
 * @Description:
 * @Date create in 3:30 2020/7/15/015
 * @Modified By:
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 获取指令类型
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
