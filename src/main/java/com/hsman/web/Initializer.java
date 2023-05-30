package com.hsman.web;

import com.hsman.web.dispatchers.CommandHandlerDispatcher;
import com.hsman.web.dispatchers.GlobalDispatcher;
import com.hsman.web.dispatchers.PlayerHandlerDispatcher;
import com.hsman.web.handlers.command.CommandHandlerManager;
import com.hsman.web.handlers.command.MultiCommandHandler;
import com.hsman.web.handlers.command.SingleCommandHandler;
import com.hsman.web.objectmanager.AmbiguousMethodMatchedException;
import com.hsman.web.dispatchers.DispatcherManager;

public class Initializer {

    static void InitDispatchers() {
        try {
            DispatcherManager dispatcherMgr = DispatcherManager.getInstance();
            dispatcherMgr.addObjectByClass(GlobalDispatcher.class);
            dispatcherMgr.addObjectByClass(PlayerHandlerDispatcher.class);
            dispatcherMgr.addObjectByClass(CommandHandlerDispatcher.class);
        } catch (AmbiguousMethodMatchedException e) {
            throw new RuntimeException(e);
        }
    }

    static void InitCommandHandlers() {
        var commandHandlerMgr = CommandHandlerManager.getInstance();
        try {
            commandHandlerMgr.addObjectByClass(MultiCommandHandler.class);
            commandHandlerMgr.addObjectByClass(SingleCommandHandler.class);
        } catch (AmbiguousMethodMatchedException e) {
            throw new RuntimeException(e);
        }
    }

    static void InitPlayerFeatureHandlers() {

    }

    static void InitHandlers() {
        InitCommandHandlers();
        InitPlayerFeatureHandlers();
    }

    public static void Initialize(){
        MainHandler.enabled = true;
        InitDispatchers();
        InitHandlers();
    }
}
