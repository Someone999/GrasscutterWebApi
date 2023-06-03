package com.hsman.web.dispatchers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hsman.utils.JsonUtils;
import com.hsman.web.annotations.Route;
import com.hsman.web.handlers.player.PlayerAttributeHandler;
import com.hsman.web.handlers.player.PlayerAttributeHandlerManager;
import com.hsman.web.requests.ApiRequest;
import com.hsman.web.requests.data.player.PlayerAttributeRequestData;
import com.hsman.web.responses.ApiResponse;
import io.javalin.http.Context;

@Route(name = "player")
public class PlayerHandlerDispatcher implements Dispatcher {
    @Override
    public void dispatch(ApiRequest request, Context context) {
        PlayerAttributeRequestData requestData = new Gson().fromJson(request.getData(), PlayerAttributeRequestData.class);
        var handler = PlayerAttributeHandlerManager.getInstance().getByRoute(requestData.getAttribute());
        if(handler == null) {
            errNoHandler(requestData, context);
            return;
        }

        switch (requestData.getOperation()) {
            case "add" -> addMethod(handler, requestData, context);
            case "get" -> getMethod(handler, requestData, context);
            case "set" -> setMethod(handler, requestData, context);
            default -> errNoOperation(requestData, context);
        }
    }

    void getMethod(PlayerAttributeHandler handler, PlayerAttributeRequestData data, Context context) {
        if(!handler.canGet()) {
            return;
        }

        handler.getValue(data, context);
    }

    void setMethod(PlayerAttributeHandler handler, PlayerAttributeRequestData data, Context context) {
        if(!handler.canSet()) {
            return;
        }

        handler.setValue(data, context);
    }

    void addMethod(PlayerAttributeHandler handler, PlayerAttributeRequestData data, Context context) {
        if(!handler.canAdd()) {
            return;
        }

        handler.addValue(data, context);
    }

    void errNoOperation(PlayerAttributeRequestData requestData, Context context) {
        JsonObject data = new JsonObject();
        data.addProperty("errorOperation", requestData.getOperation());
        data.addProperty("availableOperation", "get, set, add");
        ApiResponse response = ApiResponse.createNoOperation(data);
        response.send(context);
    }

    void errNoHandler(PlayerAttributeRequestData requestData, Context context) {
        JsonObject data = new JsonObject();
        data.addProperty("errorAttribute", requestData.getAttribute());
        var mgr = PlayerAttributeHandlerManager.getInstance();
        data.add("availableAttrs", JsonUtils.fromArray(mgr.getRoutes()));
        ApiResponse response = ApiResponse.createNoAttribute(data);
        response.send(context);
    }
}
