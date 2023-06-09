package com.hsman.config.converters;

import com.hsman.config.ConfigElement;
import com.hsman.utils.ParseUtils;

public class BooleanConverter implements ConfigConverter<Boolean> {
    @Override
    public Boolean convert(ConfigElement configElement) {
        var parseResult = ParseUtils.tryParseBoolean(configElement.getValue().toString());
        if(parseResult.isSuccess()) {
            return parseResult.getResult();
        }

        throw new ClassCastException();
    }

    @Override
    public boolean canConvertTo(Class<?> clazz) {
        return clazz == Boolean.class || clazz == boolean.class;
    }
}
