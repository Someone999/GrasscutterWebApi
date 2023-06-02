package com.hsman.config.converters.iter;

import com.hsman.config.ConfigElement;

import java.util.Iterator;
import java.util.Map;

public class EmptyConfigElementIterator implements Iterator<Map.Entry<String, ConfigElement>> {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Map.Entry<String, ConfigElement> next() {
        return null;
    }
}
