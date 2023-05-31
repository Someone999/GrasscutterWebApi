package com.hsman.config;

import lombok.Getter;

import java.io.ObjectInputFilter;
import java.util.HashMap;

public class CommonConfigElement implements ConfigElement {

    @Getter
    private final Object innerVal;
    public CommonConfigElement(Object innerVal) {
        this.innerVal = innerVal;
    }
    @Override
    @SuppressWarnings("unchecked")
    public ConfigElement get(String key) {
        if(innerVal instanceof HashMap<?, ?>) {
            return ((HashMap<String, ConfigElement>) innerVal).get(key);
        }

        return null;
    }

    @Override
    public Object getValue() {
        return innerVal;
    }
}
