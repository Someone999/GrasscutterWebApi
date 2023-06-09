package com.hsman.config.converters;

import com.hsman.config.ConfigElement;

public class IntegerConverter implements ConfigConverter<Integer> {
    @Override
    public Integer convert(ConfigElement configElement) {
        return Integer.parseInt(configElement.getValue().toString());
    }

    @Override
    public boolean canConvertTo(Class<?> clazz) {
        return clazz == Integer.class || clazz == int.class;
    }
}
