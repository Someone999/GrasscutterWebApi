package com.hsman.plugin;

import com.hsman.utils.PathUtils;
import com.hsman.web.Initializer;
import com.hsman.web.MainHandler;
import com.hsman.web.MainRouter;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;

public class HsMansPlugin extends Plugin {
    @Override
    public void onEnable() {
        Initializer.initialize();
        Grasscutter.getHttpServer().addRouter(MainRouter.class);
        var cfgFile = PathUtils.createFileInPluginPath("config.json", false);


        super.onEnable();
    }

    @Override
    public void onDisable() {
        MainHandler.enabled = false;
    }
}
