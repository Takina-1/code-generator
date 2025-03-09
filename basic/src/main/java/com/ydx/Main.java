package com.ydx;

import com.ydx.cli.CommandExecutor;

public class Main {

    public static void main(String[] args) {
//        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
//        System.out.println("args = " + args);
        commandExecutor.doExecute(args);
    }
}
