package com.hsman.config.converters;

import com.hsman.config.ConfigElement;

import java.util.Map;

public interface IterableConfigElement extends ConfigElement, Iterable<Map.Entry<String, ConfigElement>> {
}
