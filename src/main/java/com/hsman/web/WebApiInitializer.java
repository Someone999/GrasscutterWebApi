package com.hsman.web;

import com.hsman.web.dispatchers.CommandHandlerDispatcher;
import com.hsman.web.dispatchers.GlobalDispatcher;
import com.hsman.web.dispatchers.PlayerHandlerDispatcher;
import com.hsman.web.handlers.command.CommandHandlerManager;
import com.hsman.web.handlers.command.MultiCommandHandler;
import com.hsman.web.handlers.command.SingleCommandHandler;
import com.hsman.web.middlewares.ApiMiddlewareManager;
import com.hsman.web.middlewares.TokenVerifyApiMiddleware;
import com.hsman.web.objectmanager.AmbiguousMethodMatchedException;
import com.hsman.web.dispatchers.DispatcherManager;

public class WebApiInitializer {

    static void initDispatchers() {
        try {
            DispatcherManager dispatcherMgr = DispatcherManager.getInstance();
            dispatcherMgr.addObjectByClass(GlobalDispatcher.class);
            dispatcherMgr.addObjectByClass(PlayerHandlerDispatcher.class);
            dispatcherMgr.addObjectByClass(CommandHandlerDispatcher.class);
        } catch (AmbiguousMethodMatchedException e) {
            throw new RuntimeException(e);
        }
    }

    static void initCommandHandlers() {
        var commandHandlerMgr = CommandHandlerManager.getInstance();
        try {
            commandHandlerMgr.addObjectByClass(MultiCommandHandler.class);
            commandHandlerMgr.addObjectByClass(SingleCommandHandler.class);
        } catch (AmbiguousMethodMatchedException e) {
            throw new RuntimeException(e);
        }
    }

    static void initApiMiddleware() {
        try {
            ApiMiddlewareManager.getInstance().addObjectByClass(TokenVerifyApiMiddleware.class);
        } catch (AmbiguousMethodMatchedException e) {
            throw new RuntimeException(e);
        }
    }

    static void initPlayerFeatureHandlers() {

    }

    static void initHandlers() {
        initCommandHandlers();
        initPlayerFeatureHandlers();
    }

    public static void initialize(){
        MainHandler.enabled = true;
        initDispatchers();
        initHandlers();
        initApiMiddleware();
    }
}
