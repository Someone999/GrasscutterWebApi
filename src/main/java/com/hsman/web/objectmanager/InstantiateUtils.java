package com.hsman.web.objectmanager;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class InstantiateUtils {

    public static <T> Constructor<T> getBestConstructor(Class<T> clazz, Object... args) throws AmbiguousMethodMatchedException {
        ArrayList<TypeParameterPair> pairs = new ArrayList<>();
        for (Object arg : args) {
            if (arg == null) {
                pairs.add(TypeParameterPair.ReservedPair);
                continue;
            }

            pairs.add(new TypeParameterPair(arg, arg.getClass()));
        }

        var constructors = clazz.getConstructors();
        ArrayList<Constructor<T>> matchedConstructors = new ArrayList<>();
        for(var constructor : constructors) {
            var parameterTypes = constructor.getParameterTypes();
            if(parameterTypes.length != pairs.size()) {
                continue;
            }

            int matchedCount = 0;
            for(int i = 0; i < parameterTypes.length; i++) {
                var currentPair = pairs.get(i);
                var currentParamType = parameterTypes[i];

                if(currentPair.Clazz == currentParamType || currentPair == TypeParameterPair.ReservedPair) {
                    matchedCount++;
                    if(matchedCount == parameterTypes.length) {
                        matchedConstructors.add((Constructor<T>) constructor);
                        matchedCount = 0;
                    }
                }
            }
        }

        if(matchedConstructors.size() > 1) {
            throw new AmbiguousMethodMatchedException();
        }

        return matchedConstructors.get(0);
    }
}
