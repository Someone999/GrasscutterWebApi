package com.hsman.config;

import com.hsman.config.converters.IterableConfigElement;
import com.hsman.config.converters.iter.EmptyConfigElementIterator;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.ObjectInputFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommonConfigElement implements IterableConfigElement {

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

    @Override
    public void setValue(String key, ConfigElement val) {
        if(!(innerVal instanceof HashMap<?,?>)) {
            return;
        }

        ((HashMap<String, ConfigElement>) innerVal).put(key, val);
    }


    @NotNull
    @Override
    public Iterator<Map.Entry<String, ConfigElement>> iterator() {
        if(!(innerVal instanceof HashMap<?,?>)) {
            return new EmptyConfigElementIterator();
        }

        return ((HashMap<String, ConfigElement>) innerVal).entrySet().iterator();
    }
}
