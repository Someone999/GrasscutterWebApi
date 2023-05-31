package com.hsman;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hsman.config.JsonConfigElement;
import com.hsman.plugin.HsMansPlugin;
import com.hsman.utils.PathUtils;
import com.hsman.web.handlers.command.MultiCommandHandler;
import com.hsman.web.objectmanager.AmbiguousMethodMatchedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws AmbiguousMethodMatchedException, IOException {
        File f = new File("L:\\gc 3.6\\config.json");
        FileInputStream inputStream = new FileInputStream(f);
        String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        JsonConfigElement element = new JsonConfigElement(new Gson().fromJson(json, JsonObject.class));
        inputStream.close();
        return;
    }
}