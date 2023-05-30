package com.hsman.utils;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.game.player.Player;

import javax.annotation.Nullable;

public class CommandUtils {
    public static void Invoke(String command, @Nullable Player sourcePlayer, @javax.annotation.Nullable Player targetPlayer) {
        Grasscutter.getCommandMap().invoke(sourcePlayer, targetPlayer, command);
    }
}
