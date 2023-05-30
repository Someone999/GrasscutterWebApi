package com.hsman.plugin;

import com.hsman.web.Initializer;
import com.hsman.web.MainHandler;
import com.hsman.web.MainRouter;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;

public class HsMansPlugin extends Plugin {
    @Override
    public void onEnable() {
        Initializer.Initialize();
        Grasscutter.getHttpServer().addRouter(MainRouter.class);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        MainHandler.enabled = false;
    }
}
