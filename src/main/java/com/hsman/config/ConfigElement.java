package com.hsman.config;

public interface ConfigElement {
    ConfigElement get(String key);
    Object getValue();
}

