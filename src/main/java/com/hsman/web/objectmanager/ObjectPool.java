package com.hsman.web.objectmanager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ObjectPool<T> {
    ArrayList<T> members = new ArrayList<>();
    public void addObject(T obj) {
        if(members.contains(obj)) {
            return;
        }

        members.add(obj);
    }

    public void addObjectByClass(Class<? extends T> clazz, Object... args) throws AmbiguousMethodMatchedException, RuntimeException {
        if(args.length == 0) {
            try {
                var cons = clazz.getConstructor();
                members.add(cons.newInstance());
                return;
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                return;
            }
        }

       var matchedConstructors = InstantiateUtils.getBestConstructor(clazz, args);

        try {
            members.add(matchedConstructors.newInstance(args));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // Ignored
        }
    }

    public T getByClass(Class<? extends T> clazz) {
        for(var member : members) {
            if(member.getClass() == clazz) {
                return member;
            }
        }

        return null;
    }
}


