package com.hsman.config;

import org.eclipse.jetty.util.PathWatcher;

public interface ConfigElement {
    ConfigElement getConfigElement(String key);
    Object getValue();
}

