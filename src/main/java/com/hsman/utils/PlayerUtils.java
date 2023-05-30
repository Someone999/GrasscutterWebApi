package com.hsman.utils;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.database.DatabaseHelper;
import emu.grasscutter.game.player.Player;

import java.util.ArrayList;

public class PlayerUtils {
    public static Player getOnlinePlayerByUid(String uid) {
        return Grasscutter.getGameServer().getPlayerByUid(Integer.parseInt(uid));
    }

    public static Player getOnlinePlayerByAccountId(String accountId) {
        return Grasscutter.getGameServer().getPlayerByAccountId(accountId);
    }

    public static Player[] getOnlinePlayersByPlayerName(String playerName) {
        ArrayList<Player> matchedPlayers = new ArrayList<>();
        var players = Grasscutter.getGameServer().getPlayers();
        for(Player p :players.values()) {
            if(p.getAccount().getUsername().equals(playerName)) {
                matchedPlayers.add(p);
            }
        }

        return matchedPlayers.toArray(Player[]::new);
    }

    public static Player getDatabasePlayerByUid(String uid) {
        return DatabaseHelper.getPlayerByUid(Integer.parseInt(uid));
    }

    public static Player getDatabasePlayerByName(String name) {
        var account = DatabaseHelper.getAccountByName(name);
        return DatabaseHelper.getPlayerByAccount(account, Player.class);
    }


}
