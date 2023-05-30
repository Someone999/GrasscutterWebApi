package com.hsman.web;

import emu.grasscutter.server.http.Router;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class MainRouter implements Router {
    @Override
    public void applyRoutes(Javalin javalin) {
        javalin.post("hsman/api", new MainHandler());
    }
}

