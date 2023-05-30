package com.hsman.web.dispatchers;

import com.hsman.web.objectmanager.ObjectPool;
import com.hsman.web.objectmanager.RouteObjectPool;

public class DispatcherManager extends RouteObjectPool<Dispatcher> {
    private DispatcherManager() {
    }

    static DispatcherManager instance;
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
