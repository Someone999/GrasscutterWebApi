package com.hsman.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonConfigElement implements ConfigElement {

    HashMap<String, ConfigElement> configElementHashMap;

    static Object[] expendList(JsonArray jsonArray) {
        ArrayList<Object> objs = new ArrayList<>();
        for(var elem : jsonArray) {

            if(jsonArray.isJsonNull()) {
                objs.add(null);
                continue;
            }

            if(elem.isJsonPrimitive()) {
                objs.add(elem.getAsString());
                continue;
            }

            if(elem.isJsonObject()) {
                objs.add(expendObject(elem.getAsJsonObject()));
                continue;
            }

            if(elem.isJsonArray()) {
                objs.add(expendList(elem.getAsJsonArray()));
            }
        }

        return objs.toArray(Object[]::new);
    }

    static HashMap<String, ConfigElement> expendObject(JsonObject jsonObject) {
        HashMap<String, ConfigElement> configElementHashMap = new HashMap<>();
        for(var entry : jsonObject.entrySet()) {
            var val = entry.getValue();
            if(val.isJsonPrimitive()) {
                configElementHashMap.put(entry.getKey(), new CommonConfigElement(entry.getValue().getAsString()));
                continue;
            }

            if(val.isJsonObject()) {
                configElementHashMap.put(entry.getKey(), new CommonConfigElement(expendObject(val.getAsJsonObject())));
                continue;
            }

            if(val.isJsonArray()) {
                configElementHashMap.put(entry.getKey(), new CommonConfigElement(expendList(val.getAsJsonArray())));
            }
        }

        return configElementHashMap;
    }

    public JsonConfigElement(JsonObject jsonObject) {
        configElementHashMap = expendObject(jsonObject);
    }
    @Override
    public ConfigElement get(String key) {
        return configElementHashMap.get(key);
    }

    @Override
    public Object getValue() {
        return null;
    }
}
