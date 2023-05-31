package com.hsman.config.converters;

import com.hsman.config.ConfigElement;

public interface ConfigConverter<T> {
    T convert(ConfigElement configElement);
}

