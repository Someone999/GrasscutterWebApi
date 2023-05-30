package com.hsman.utils;

import com.google.gson.JsonArray;

public class JsonUtils {
    public static JsonArray fromArray(String[] arr) {
        JsonArray array = new JsonArray();
        for (var item : arr) {
            array.add(item);
        }

        return array;
    }
}
