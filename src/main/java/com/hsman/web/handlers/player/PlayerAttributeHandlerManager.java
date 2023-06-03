package com.hsman.web.handlers.player;

import com.hsman.web.objectmanager.RouteObjectContainer;

public class PlayerAttributeHandlerManager extends RouteObjectContainer<PlayerAttributeHandler> {
    private PlayerAttributeHandlerManager() {
    }

    volatile static PlayerAttributeHandlerManager instance;
    static final Object locker = new Object();
    public static PlayerAttributeHandlerManager getInstance() {
        if(instance == null) {
            instance = new PlayerAttributeHandlerManager();
        }

        synchronized (locker) {
            if(instance == null) {
                instance = new PlayerAttributeHandlerManager();
            }

            return instance;
        }
    }

}
