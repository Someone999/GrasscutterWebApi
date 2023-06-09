package com.hsman.plugin;

import com.hsman.config.CommonConfigElement;
import com.hsman.config.ConfigInitializer;
import com.hsman.config.Configs;
import com.hsman.plugin.eventlistener.EventListeners;
import com.hsman.utils.RandomStringUtils;
import com.hsman.web.MainRouter;
import com.hsman.web.WebApiInitializer;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.game.ReceiveCommandFeedbackEvent;

import java.security.SecureRandom;

public class HsMansPlugin extends Plugin {
    @Override
    public void onEnable() {
        WebApiInitializer.initialize();
        ConfigInitializer.initialize();
        if(Configs.mainConfig.get("token").getValue().toString().isEmpty()) {
            var randToken = RandomStringUtils.generateRandomString(32);
            Configs.mainConfig.setValue("token", new CommonConfigElement(randToken));
            Configs.mainConfig.save();
        }
        Grasscutter.getHttpServer().addRouter(MainRouter.class);
        new EventHandler<>(ReceiveCommandFeedbackEvent.class)
                .priority(HandlerPriority.HIGH)
                .listener(EventListeners::OnReceiveCommandFeedbackEvent).register(this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        Configs.mainConfig.setValue("enabledWebApi", new CommonConfigElement(false));
    }
}
