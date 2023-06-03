package com.hsman.web.handlers.player;

import com.hsman.web.requests.data.player.PlayerAttributeRequestData;
import io.javalin.http.Context;


public interface PlayerAttributeHandler {
    void getValue(PlayerAttributeRequestData request, Context context);
    void setValue(PlayerAttributeRequestData request, Context context);
    void addValue(PlayerAttributeRequestData request, Context context);
    boolean canGet();
    boolean canSet();
    boolean canAdd();

}
