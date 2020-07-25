package com.cinder.im.protocol.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:46 2020/7/18/018
 * @Modified By:
 */
public class TimeUtil {
    public static String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
