package com.hsman.web.middlewares;

import com.hsman.web.requests.ApiRequest;
import io.javalin.http.Context;

public interface ApiMiddleware {
   void setNextMiddleware(ApiMiddleware apiMiddleware);
   MiddlewareResult execute(ApiRequest apiRequest, Context context);
}

