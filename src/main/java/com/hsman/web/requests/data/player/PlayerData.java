package com.hsman.web.requests.data.player;

import com.hsman.utils.PlayerUtils;
import emu.grasscutter.game.player.Player;
import lombok.Getter;

public class PlayerData {
    @Getter
    boolean playerOnline;

    @Getter
    String playerId;

    //Definition is in PlayerIdType.java
    @Getter
    int idType;

    public Player getPlayer() {
        if(playerId == null) {
            return null;
        }

        if(playerOnline)
        {
            return switch (idType) {
                //UID
                case 0 -> PlayerUtils.getOnlinePlayerByUid(playerId);
                //USERNAME
                case 1 -> {
                    var players = PlayerUtils.getOnlinePlayersByPlayerName(playerId);
                    if(players.length > 1) {
                        yield null;
                    }

                    yield players[0];
                }
                //ACCOUNT_ID
                case 2 -> PlayerUtils.getOnlinePlayerByAccountId(playerId);
                default -> null;
            };
        }
        else {
            return switch (idType) {
                //UID
                case 0 -> PlayerUtils.getDatabasePlayerByUid(playerId);
                //USERNAME
                case 1 -> PlayerUtils.getDatabasePlayerByName(playerId);
                default -> null;
            };
        }

    }
}
