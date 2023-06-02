package com.hsman.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

public class JsonUtils {
    public static JsonArray fromArray(String[] arr) {
        JsonArray array = new JsonArray();
        for (var item : arr) {
            array.add(item);
        }

        return array;
    }

    public static Object jsonPrimitiveConverter(JsonPrimitive jsonPrimitive) {
        if(jsonPrimitive.isBoolean()) {
            return jsonPrimitive.getAsBoolean();
        }

        if(jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsNumber();
        }

        if(jsonPrimitive.isString()) {
            return jsonPrimitive.getAsString();
        }

        if(jsonPrimitive.isJsonNull()) {
            return null;
        }

        throw new IllegalStateException();
    }
}
