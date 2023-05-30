package com.hsman.web.requests.data.player;

import lombok.Getter;

public class PlayerIdType {
    public static PlayerIdType UID = new PlayerIdType(0);
    public static PlayerIdType USERNAME = new PlayerIdType(1);
    public static PlayerIdType ACCOUNT_ID = new PlayerIdType(2);

    @Getter
    int value;
    PlayerIdType(int i){
        value = i;
    }
}
