package com.hsman.config;

import java.util.HashMap;

public interface ConfigElement {
    ConfigElement get(String key);
    Object getValue();
    void setValue(String key, ConfigElement val);
}

