package com.hsman.web.dispatchers;

import com.hsman.web.requests.ApiRequest;
import io.javalin.http.Context;

public interface Dispatcher {
    void dispatch(ApiRequest request, Context context);
}
