package com.hsman.web.objectmanager;

import com.hsman.web.annotations.Route;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class RouteObjectPool<T> {
    HashMap<String, T> members = new HashMap<>();
    static String getRouteName(Class<?> clazz) {
        var routeAnnotation = clazz.getAnnotation(Route.class);
        if(routeAnnotation == null) {
            throw new IllegalStateException("This class has no annotation named Route.");
        }
        return routeAnnotation.name();
    }

    static String getRouteName(Object obj) {
        return getRouteName(obj.getClass());
    }


    public void addObject(T obj) {

        var routeName = getRouteName(obj);
        if(members.containsKey(routeName)) {
            return;
        }

        members.put(routeName, obj);
    }

    public void addObjectByClass(Class<? extends T> clazz, Object... args) throws AmbiguousMethodMatchedException {

        var routeName = getRouteName(clazz);
        if(args.length == 0) {
            try {
                var cons = clazz.getConstructor();
                members.put(routeName, cons.newInstance());
                return;
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                return;
            }
        }

        var matchedConstructors = InstantiateUtils.getBestConstructor(clazz, args);
        try {
            members.put(routeName, matchedConstructors.newInstance(args));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // Ignored
        }
    }

    public T getByRoute(String route) {
        for(var member : members.entrySet()) {
            if(member.getKey().equals(route)) {
                return member.getValue();
            }
        }

        return null;
    }

    public String[] getRoutes() {
        ArrayList<String> routes = new ArrayList<>(members.keySet());
        return routes.toArray(String[]::new);
    }
}
