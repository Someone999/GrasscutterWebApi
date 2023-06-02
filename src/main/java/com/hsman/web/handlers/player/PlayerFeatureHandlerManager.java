package com.hsman.web.handlers.player;

import com.hsman.web.objectmanager.RouteObjectContainer;

public class PlayerFeatureHandlerManager extends RouteObjectContainer<PlayerFeatureHandler> {
    private PlayerFeatureHandlerManager() {
    }

    volatile static PlayerFeatureHandlerManager instance;
    static final Object locker = new Object();
    public static PlayerFeatureHandlerManager getInstance() {
        if(instance == null) {
            instance = new PlayerFeatureHandlerManager();
        }

        synchronized (locker) {
            if(instance == null) {
                instance = new PlayerFeatureHandlerManager();
            }

            return instance;
        }
    }

}
