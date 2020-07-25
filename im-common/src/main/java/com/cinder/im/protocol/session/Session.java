package com.cinder.im.protocol.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:58 2020/7/19/019
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    /**
     * 用户唯一标识
     */
    private String useId;
    private String username;
}
