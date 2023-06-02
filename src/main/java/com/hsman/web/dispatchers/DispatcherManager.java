package com.hsman.web.dispatchers;

import com.hsman.web.objectmanager.RouteObjectContainer;

public class DispatcherManager extends RouteObjectContainer<Dispatcher> {
    private DispatcherManager() {
    }

    volatile static DispatcherManager instance;
    static final Object locker = new Object();
    public static DispatcherManager getInstance() {
        if(instance == null) {
            instance = new DispatcherManager();
        }

        synchronized (locker) {
            if(instance == null) {
                instance = new DispatcherManager();
            }
            return instance;
        }
    }
}
