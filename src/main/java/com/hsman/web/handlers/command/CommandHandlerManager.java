package com.hsman.web.handlers.command;

import com.hsman.web.handlers.player.PlayerFeatureHandlerManager;
import com.hsman.web.objectmanager.RouteObjectPool;

public class CommandHandlerManager extends RouteObjectPool<CommandHandler> {
    private CommandHandlerManager() {
    }

    static CommandHandlerManager instance;
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
