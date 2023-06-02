package com.hsman.web.handlers.command;

import com.hsman.web.objectmanager.RouteObjectContainer;

public class CommandHandlerManager extends RouteObjectContainer<CommandHandler> {
    private CommandHandlerManager() {
    }

    volatile static CommandHandlerManager instance;
    static final Object locker = new Object();
    public static CommandHandlerManager getInstance() {
        if(instance == null) {
            instance = new CommandHandlerManager();
        }

        synchronized (locker) {
            if(instance == null) {
                instance = new CommandHandlerManager();
            }

            return instance;
        }
    }
}
