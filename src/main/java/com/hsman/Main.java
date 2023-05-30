package com.hsman;

import com.hsman.plugin.HsMansPlugin;
import com.hsman.utils.PathUtils;
import com.hsman.web.handlers.command.MultiCommandHandler;
import com.hsman.web.objectmanager.AmbiguousMethodMatchedException;

public class Main {
    public static void main(String[] args) throws AmbiguousMethodMatchedException {
        new HsMansPlugin().onEnable();
        return;
    }
}