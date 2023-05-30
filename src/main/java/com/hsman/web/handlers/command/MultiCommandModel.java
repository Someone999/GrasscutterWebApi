package com.hsman.web.handlers.command;

import com.hsman.web.requests.data.player.PlayerData;
import lombok.Getter;

public class MultiCommandModel {
    @Getter
    PlayerData sourcePlayer = new PlayerData();

    @Getter
    PlayerData targetPlayer = new PlayerData();

    @Getter
    String command;

    @Getter
    int executeCount = 1;
}
