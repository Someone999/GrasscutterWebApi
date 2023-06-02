package com.hsman.web.dispatchers;

import com.google.gson.JsonObject;
import com.hsman.utils.JsonUtils;
import com.hsman.web.annotations.Route;
import com.hsman.web.help.ArgumentDescriptions;
import com.hsman.web.middlewares.ApiMiddleware;
import com.hsman.web.middlewares.ApiMiddlewareManager;
import com.hsman.web.requests.ApiRequest;
import com.hsman.web.responses.ApiResponse;
import io.javalin.http.Context;

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

        var middlewares = ApiMiddlewareManager.getInstance().getAllMembers();
        for(int i = 0; i < middlewares.length ; i++) {
            ApiMiddleware nextMiddleware = null;
            if(i + 1 < middlewares.length) {
                nextMiddleware = (ApiMiddleware) middlewares[i + 1];
            }

            ((ApiMiddleware) middlewares[i]).setNextMiddleware(nextMiddleware);
        }

        var firstMiddleware = (ApiMiddleware) middlewares[0];
        var middlewareResult = firstMiddleware.execute(request, context);

        if(middlewareResult.abort) {
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
