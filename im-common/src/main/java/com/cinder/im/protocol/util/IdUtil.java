package com.cinder.im.protocol.util;

import java.util.UUID;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:40 2020/7/19/019
 * @Modified By:
 */
public class IdUtil {
    public static String  randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
