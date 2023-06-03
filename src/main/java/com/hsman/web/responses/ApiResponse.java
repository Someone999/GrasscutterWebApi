package com.hsman.web.responses;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.javalin.http.Context;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ApiResponse {
    int retCode;
    String message;
    JsonObject data;

    public ApiResponse(int retCode, String message, JsonObject data) {
        this.retCode = retCode;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse createSuccess(JsonObject data) {
        return new ApiResponse(0, "", data);
    }

    public static ApiResponse createInvalidRequest(JsonObject data) {
        return new ApiResponse(-1, "Invalid request", data);
    }

    public static ApiResponse createNoOperation(JsonObject data) {
        return new ApiResponse(-2, "No such operator", data);
    }

    public static ApiResponse createNoAttribute(JsonObject data) {
        return new ApiResponse(-3, "No such attribute", data);
    }

    public static ApiResponse createNoHandler(JsonObject data) {
        return new ApiResponse(-4, "No such handler", data);
    }

    public static ApiResponse createNoDispatcher(JsonObject data) {
        return new ApiResponse(-5, "No such dispatcher", data);
    }

    public static ApiResponse createApiHandlingException(JsonObject data) {
        return new ApiResponse(-6, "Exception occurred during api handling.", data);
    }

    public static ApiResponse createTokenMismatch(JsonObject data) {
        return new ApiResponse(-7, "Token mismatch", data);
    }

    public void send(Context context) {
        Gson gson = new Gson();
        try {
            context.res.getOutputStream().write(gson.toJson(this).getBytes(StandardCharsets.UTF_8));
            context.res.setStatus(200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
