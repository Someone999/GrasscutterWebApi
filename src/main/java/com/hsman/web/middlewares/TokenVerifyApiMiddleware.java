package com.hsman.web.middlewares;

import com.hsman.config.Configs;
import com.hsman.web.requests.ApiRequest;
import com.hsman.web.responses.ApiResponse;
import io.javalin.http.Context;

public class TokenVerifyApiMiddleware implements ApiMiddleware {

    ApiMiddleware nextApiMiddleware;
    @Override
    public void setNextMiddleware(ApiMiddleware apiMiddleware) {
        this.nextApiMiddleware = apiMiddleware;
    }

    @Override
    public MiddlewareResult execute(ApiRequest apiRequest, Context context) {

        var configToken = Configs.mainConfig.get("token").getValue();
        if(!(configToken instanceof String) || !configToken.equals(apiRequest.getToken())) {
            ApiResponse.createTokenMismatch(null).send(context);
            return MiddlewareResult.builder().abort(true).build();
        }

        if(nextApiMiddleware != null) {
            return nextApiMiddleware.execute(apiRequest, context);
        }

        return MiddlewareResult.builder().build();
    }
}
