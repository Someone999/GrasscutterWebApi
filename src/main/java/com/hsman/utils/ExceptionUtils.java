package com.hsman.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;

public class ExceptionUtils {
    public static JsonArray ExceptionStackTrace(Exception exception) {
        var stackTraceArr = exception.getStackTrace();
        JsonArray jsonArray = new JsonArray();
        for(var stackTrace :stackTraceArr) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("fileName", stackTrace.getFileName());
            jsonObject.addProperty("method", stackTrace.getMethodName());
            jsonObject.addProperty("line", stackTrace.getLineNumber());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
