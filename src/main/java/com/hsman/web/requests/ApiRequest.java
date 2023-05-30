package com.hsman.web.requests;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class ApiRequest {
    @Getter
    String token;
    @Getter
    String type;
    @Getter
    JsonObject data;
}

