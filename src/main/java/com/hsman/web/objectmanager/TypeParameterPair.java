package com.hsman.web.objectmanager;

import lombok.Getter;
import lombok.Setter;

class TypeParameterPair {
    @Getter
    @Setter
    Object parameter;

    @Getter
    @Setter
    Class<?> Clazz;

    public TypeParameterPair(Object parameter, Class<?> clazz) {
        this.parameter = parameter;
        Clazz = clazz;
    }

    public static TypeParameterPair ReservedPair = new TypeParameterPair(null, null);
}
