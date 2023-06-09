package com.hsman.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hsman.config.converters.ConfigConverter;
import com.hsman.config.converters.IterableConfigElement;
import com.hsman.utils.PathUtils;

import java.io.*;
import java.util.HashMap;

public class MainConfig implements FileBasedConfigElement {
    private JsonConfigElement configElement;
    private String filePath;
    public MainConfig(String relatePath) {
        var f = PathUtils.createFileInPluginPath(relatePath, false);
        if(!f.isExists()) {
            return;
        }
        Gson gson = new Gson();
        try {
            filePath = relatePath;
            FileInputStream inputStream = new FileInputStream(f.getResult());
            String json = new String(inputStream.readAllBytes());
            configElement = new JsonConfigElement(gson.fromJson(json, JsonObject.class));
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public MainConfig() {
        this("config.json");
    }

    @Override
    public ConfigElement get(String key) {
        return configElement.get(key);
    }

    @Override
    public Object getValue() {
        if(configElement == null) {
            return ConfigNullValue.INSTANCE;
        }
        return configElement;
    }

    @Override
    public void setValue(String key, ConfigElement val) {
        configElement.setValue(key, val);
    }

    @Override
    public <T> T convert(ConfigConverter<T> converter) {
        if (converter.canConvertTo(getClass())) {
            return converter.convert(this);
        }
        throw new ClassCastException("Converter can not convert " + getClass().getName() + " to target class");

    }

    HashMap<String, Object> getHashMap(IterableConfigElement element) {
        HashMap<String, Object> hashMap = new HashMap<>();
        for(var configItem : element) {
            var currentCfgElem = configItem.getValue();
            var currentCfgValue = currentCfgElem.getValue();
            var isCurrentCfgElemIterable = currentCfgElem instanceof IterableConfigElement;
            var isCurrentValueHashMap = currentCfgValue instanceof HashMap<?,?>;

            if(isCurrentCfgElemIterable && isCurrentValueHashMap) {
                hashMap.put(configItem.getKey(), getHashMap((IterableConfigElement) configItem.getValue()));
            } else {
                var val = configItem.getValue().getValue();
                if(val == ConfigNullValue.INSTANCE) {
                    val = null;
                }
                hashMap.put(configItem.getKey(), val);
            }
        }

        return hashMap;
    }

    @Override
    public void save() {
        Gson gson = new Gson();
        var json = gson.toJson(getHashMap(configElement));
        File f = new File(filePath);
        if(!f.exists()) {
            return;
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(f);
            var channel = outputStream.getChannel();
            channel.position(0);
            outputStream.write(json.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
