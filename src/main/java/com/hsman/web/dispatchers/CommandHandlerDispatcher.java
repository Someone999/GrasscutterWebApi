package com.hsman.web.dispatchers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hsman.utils.JsonUtils;
import com.hsman.web.annotations.Route;
import com.hsman.web.handlers.command.CommandHandlerManager;
import com.hsman.web.requests.ApiRequest;
import com.hsman.web.requests.data.CommandRequestData;
import com.hsman.web.responses.ApiResponse;
import io.javalin.http.Context;

@Route(name = "command")
public class CommandHandlerDispatcher implements Dispatcher {
    @Override
    public void dispatch(ApiRequest request, Context context) {
        var commandReqData = new Gson().fromJson(request.getData(), CommandRequestData.class);
        var commandHandlerMgr = CommandHandlerManager.getInstance();
        var handler = commandHandlerMgr.getByRoute(commandReqData.getType());

        if(handler == null) {
            JsonObject data = new JsonObject();
            data.addProperty("errorHandler", commandReqData.getType());
            data.add("availableHandlers", JsonUtils.fromArray(commandHandlerMgr.getRoutes()));
            ApiResponse.createNoHandler(data).send(context);
            return;
        }

        handler.handle(commandReqData, context);
    }
}
