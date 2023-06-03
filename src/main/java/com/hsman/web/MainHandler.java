package com.hsman.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hsman.utils.ExceptionUtils;
import com.hsman.web.dispatchers.DispatcherManager;
import com.hsman.web.requests.ApiRequest;
import com.hsman.web.responses.ApiResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class MainHandler implements Handler {

    public static boolean enabled;
    static void globalDispatchExceptionHandler(Exception exception, Context context) {
        JsonObject data = new JsonObject();
        data.addProperty("message", exception.getMessage());
        data.add("stackTrace", ExceptionUtils.ExceptionStackTrace(exception));
        ApiResponse.createApiHandlingException(data).send(context);
    }

    static void topLevelExceptionHandler(Exception exception, Context context) {
        JsonObject data = new JsonObject();
        data.addProperty("message", exception.getMessage());
        data.add("stackTrace", ExceptionUtils.ExceptionStackTrace(exception));
        ApiResponse.createInvalidRequest(data).send(context);
    }

    public void handleNoTopLevelExceptionHandler(@NotNull Context context) throws Exception {
        if(!enabled) {
            context.res.sendError(404);
            return;
        }

        var inputStream = context.req.getInputStream();
        try(inputStream)
        {
            var allBytes = inputStream.readAllBytes();
            var reqString = new String(allBytes, StandardCharsets.UTF_8);
            var reqObj = new Gson().fromJson(reqString, ApiRequest.class);
            try {
                DispatcherManager.getInstance().getByRoute("<Global>").dispatch(reqObj, context);
            }
            catch (Exception exception) {
                globalDispatchExceptionHandler(exception, context);
            }
        }
    }


    @Override
    public void handle(@NotNull Context context) {
        try {
            handleNoTopLevelExceptionHandler(context);
        } catch(Exception exception) {
            topLevelExceptionHandler(exception, context);
        }
    }
}
