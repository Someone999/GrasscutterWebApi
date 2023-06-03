package com.hsman.web.requests.data.player;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class PlayerAttributeRequestData {

    @Getter
    PlayerData playerData = new PlayerData();

    @Getter
    @SerializedName("attr")
    String attribute;

    @Getter
    @SerializedName("op")
    String operation;

    @Getter
    JsonObject data;
}
