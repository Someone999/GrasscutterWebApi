package com.hsman.web.requests.data;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.hsman.web.requests.data.player.PlayerData;
import emu.grasscutter.game.player.Player;
import lombok.Getter;

public class CommandRequestData {

    @Getter
    @SerializedName("type")
    String type;

    @Getter
    PlayerData sourcePlayer = new PlayerData();

    @Getter
    PlayerData targetPlayer = new PlayerData();

    @Getter
    JsonObject data;

}
