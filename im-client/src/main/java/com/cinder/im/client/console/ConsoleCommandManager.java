package com.cinder.im.client.console;

import com.cinder.im.client.console.chat.GroupChatConsoleCommand;
import com.cinder.im.client.console.group.CreatGroupConsoleCommand;
import com.cinder.im.client.console.group.JoinGroupConsoleCommand;
import com.cinder.im.client.console.group.QuitGroupConsoleCommand;
import com.cinder.im.client.console.list.ListGroupMembersConsoleCommand;
import com.cinder.im.client.console.list.UserIdListConsoleCommand;
import com.cinder.im.client.console.chat.PrivateChatConsoleCommand;
import com.cinder.im.client.status.InputStatus;
import com.cinder.im.protocol.session.Session;
import com.cinder.im.protocol.util.SessionUtil;
import io.netty.channel.Channel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:53 2020/7/19/019
 * @Modified By:
 */
@NoArgsConstructor
public class ConsoleCommandManager extends AbstractConsoleCommand {
    private Map<String, AbstractConsoleCommand> consoleCommandMap;
    public static InputStatus inputStatus = InputStatus.COMMAND;
    private Channel channel;
    private Scanner scanner;
    /**
     * 退出私聊或群聊模式命令
     */
    public static final String QUIT = "quit";


    public ConsoleCommandManager(Channel channel, Scanner scanner) {
        this.channel = channel;
        this.scanner = scanner;
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("list",new UserIdListConsoleCommand());
        consoleCommandMap.put("private", new PrivateChatConsoleCommand());
        consoleCommandMap.put("createGroup",new CreatGroupConsoleCommand());
        consoleCommandMap.put("joinGroup",new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup",new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers",new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("group",new GroupChatConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner,Channel channel) {
        this.scanner = scanner;
        this.channel = channel;
        exec();
    }

    public void exec(){
        String command = scanner.nextLine();

        if (!SessionUtil.hasLogin(channel)) {
            return;
        }
        AbstractConsoleCommand abstractConsoleCommand = consoleCommandMap.get(command);
        if (abstractConsoleCommand != null) {
            abstractConsoleCommand.exec(scanner,channel);
        }else{
            System.out.println("无效指令【" + command + "】，请重新输入！");
        }
    }
}
