package com.hsman.plugin;

import com.hsman.plugin.eventlistener.EventListeners;
import com.hsman.web.WebApiInitializer;
import com.hsman.web.MainHandler;
import com.hsman.web.MainRouter;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.game.ReceiveCommandFeedbackEvent;

public class HsMansPlugin extends Plugin {
    @Override
    public void onEnable() {
        WebApiInitializer.initialize();
        Grasscutter.getHttpServer().addRouter(MainRouter.class);
        new EventHandler<>(ReceiveCommandFeedbackEvent.class)
                .priority(HandlerPriority.HIGH)
                .listener(EventListeners::OnReceiveCommandFeedbackEvent).register(this);


        super.onEnable();
    }

    @Override
    public void onDisable() {
        MainHandler.enabled = false;
    }
}
