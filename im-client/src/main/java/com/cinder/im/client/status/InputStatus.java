package com.cinder.im.client.status;


/**
 * @author Cinder
 * @Description: 客户端当前输入状态
 * @Date create in 1:59 2020/7/20/020
 * @Modified By:
 */
public enum InputStatus {
    /**
     * 命令模式
     */
    COMMAND("命令模式",""),
    /**
     * 私聊模式
     */
    PRIVATE("私聊模式"),
    /**
     * 群聊模式
     */
    GROUP("群聊模式");

    private String description;
    /**
     * 私聊模式目标Id或群聊模式群Id
     */
    private String id;
    InputStatus(String description){
        this.description = description;
    }

    InputStatus(String description, String id) {
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
}
