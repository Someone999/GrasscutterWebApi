package com.hsman.web.dispatchers;

import com.google.gson.JsonObject;
import com.hsman.utils.JsonUtils;
import com.hsman.web.annotations.Route;
import com.hsman.web.help.ArgumentDescription;
import com.hsman.web.help.ArgumentDescriptions;
import com.hsman.web.requests.ApiRequest;
import com.hsman.web.responses.ApiResponse;
import emu.grasscutter.Grasscutter;
import io.javalin.http.Context;

import java.io.IOException;
@Route(name = "<Global>")
public class GlobalDispatcher implements Dispatcher {
    @Override
    public void dispatch(ApiRequest request, Context context) {
        if(request == null || request.getType() == null || request.getToken() == null) {
            JsonObject data = new JsonObject();
            data.addProperty("error", "ApiRequest not existed or incomplete");
            data.add("help", ArgumentDescriptions.getBasicRequestArgumentDescription().asJsonObject());
            ApiResponse.createInvalidRequest(data).send(context);
            return;
        }

        var dispatchMgr = DispatcherManager.getInstance();
        var dispatcher = dispatchMgr.getByRoute(request.getType());
        if(dispatcher == null) {
            JsonObject data = new JsonObject();
            data.add("availableDispatchers", JsonUtils.fromArray(dispatchMgr.getRoutes()));
            ApiResponse.createNoDispatcher(data).send(context);
            return;
        }

        dispatcher.dispatch(request, context);
    }
}
