package com.hsman.config;

public class ConfigNullValue {
    private ConfigNullValue(){
    }
    @Override
    public boolean equals(Object obj) {
        return obj == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }

    public final static ConfigNullValue INSTANCE = new ConfigNullValue();
}
