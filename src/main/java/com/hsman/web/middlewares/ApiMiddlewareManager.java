package com.hsman.web.middlewares;

import com.hsman.web.objectmanager.ObjectContainer;

import java.util.function.Supplier;

public class ApiMiddlewareManager extends ObjectContainer<ApiMiddleware> {
    private ApiMiddlewareManager() {
    }
    static final Object locker = new Object();
    volatile static ApiMiddlewareManager instance;
    public static ApiMiddlewareManager getInstance() {
        if(instance != null) {
            return instance;
        }


        synchronized (locker) {
            if(instance != null) {
                return instance;
            }

            return instance = new ApiMiddlewareManager();
        }
    }
}
