package com.hsman.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hsman.config.converters.IterableConfigElement;
import com.hsman.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonConfigElement implements IterableConfigElement {

    HashMap<String, ConfigElement> configElementHashMap;

    static Object[] expendList(JsonArray jsonArray) {
        ArrayList<Object> objs = new ArrayList<>();
        for(var elem : jsonArray) {

            if(jsonArray.isJsonNull()) {
                objs.add(null);
                continue;
            }

            if(elem.isJsonPrimitive()) {
                var jsonVal = JsonUtils.jsonPrimitiveConverter(elem.getAsJsonPrimitive());
                objs.add(jsonVal);
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
                var jsonVal = JsonUtils.jsonPrimitiveConverter(entry.getValue().getAsJsonPrimitive());
                configElementHashMap.put(entry.getKey(), new CommonConfigElement(jsonVal));
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
        return configElementHashMap;
    }

    @Override
    public void setValue(String key, ConfigElement val) {
        if(configElementHashMap.containsKey(key)) {
            configElementHashMap.put(key, val);
        }
    }


    @NotNull
    @Override
    public Iterator<Map.Entry<String, ConfigElement>> iterator() {
        return configElementHashMap.entrySet().iterator();
    }
}
