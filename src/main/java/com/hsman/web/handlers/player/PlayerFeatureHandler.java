package com.hsman.web.handlers.player;

import com.hsman.web.requests.data.player.PlayerFeatureRequestData;
import io.javalin.http.Context;


public interface PlayerFeatureHandler {
    void getValue(PlayerFeatureRequestData request, Context context);
    void setValue(PlayerFeatureRequestData request, Context context);
    void addValue(PlayerFeatureRequestData request, Context context);
    boolean canGet();
    boolean canSet();
    boolean canAdd();

}
