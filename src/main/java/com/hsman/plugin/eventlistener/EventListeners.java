package com.hsman.plugin.eventlistener;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.server.event.game.ReceiveCommandFeedbackEvent;
import lombok.Getter;

public class EventListeners {

    @Getter
    private static String lastCommandOutput = "";
    public static void OnReceiveCommandFeedbackEvent(ReceiveCommandFeedbackEvent receiveCommandFeedbackEvent) {
        lastCommandOutput = receiveCommandFeedbackEvent.getMessage();
    }
}
