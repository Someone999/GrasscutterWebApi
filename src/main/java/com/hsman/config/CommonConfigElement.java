package com.hsman.config;

import com.hsman.config.converters.ConfigConverter;
import com.hsman.config.converters.IterableConfigElement;
import com.hsman.config.converters.iter.EmptyConfigElementIterator;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CommonConfigElement implements IterableConfigElement {

    @Getter
    private final Object innerVal;
    public CommonConfigElement(Object innerVal) {
        this.innerVal = innerVal == null ? ConfigNullValue.INSTANCE : innerVal;
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

    @Override
    public <T> T convert(ConfigConverter<T> converter) {
        if (converter.canConvertTo(getClass())) {
            return converter.convert(this);
        }

        throw new ClassCastException("Converter can not convert " + getClass().getName() + " to target class");

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
