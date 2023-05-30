package com.hsman.web.help;

public class ArgumentDescriptions {
    public static ArgumentDescription getPlayerDataArgumentDescription() {
        var playerDataDesc = ArgumentDescription.builder()
                .description("执行、目标玩家信息，留空则会使用null")
                .name("playerData")
                .type("Object")
                .optional(true)
                .build();


        playerDataDesc.
                addSubArgumentDescription("sourcePlayer",
                        "String", "执行玩家的uid，PlayerName，或者AccountId", false);
        playerDataDesc.
                addSubArgumentDescription("targetPlayer",
                        "String", "目标玩家的uid，PlayerName，或者AccountId", false);

        playerDataDesc.
                addSubArgumentDescription("idType", "int",
                        "0代表uid, 1代表PlayerName, 2代表AccountId", false);

        return playerDataDesc;
    }

    public static ArgumentDescription getBasicRequestArgumentDescription() {
        ArgumentDescription basicReqArgDesc = ArgumentDescription.builder()
                .name("")
                .type("Object")
                .description("Base request")
                .optional(false)
                .build();

        basicReqArgDesc.addSubArgumentDescription("token", "String", "Token to authenticate", false);
        basicReqArgDesc.addSubArgumentDescription("type", "String", "Command type", false);
        basicReqArgDesc.addSubArgumentDescription("data", "Object", "Additional data", true);
        return basicReqArgDesc;
    }
}
