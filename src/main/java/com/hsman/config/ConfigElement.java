package com.hsman.config;

import com.hsman.config.converters.ConfigConverter;

import java.util.HashMap;

public interface ConfigElement {
    ConfigElement get(String key);
    Object getValue();
    void setValue(String key, ConfigElement val);
    <T> T convert(ConfigConverter<T> converter);
}

