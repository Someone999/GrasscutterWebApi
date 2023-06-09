package com.hsman.config;

import com.hsman.utils.PathUtils;
import com.hsman.utils.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigInitializer {
    public static void initialize() {
        var cfgFile = PathUtils.getFileInPluginPath("config.json", true);
        if(!cfgFile.isCreated() && !cfgFile.isExists()) {
            throw new IllegalStateException("Cannot create config file");
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(cfgFile.getResult());
            var resCfgFile = ResourceUtils.getResourceFile("defaultFiles/config.json");
            if(!resCfgFile.isSuccess()) {
                resCfgFile = ResourceUtils.getResourceFile("/defaultFiles/config.json");
            }

            var bytesReadResult = resCfgFile.getResult().readAllBytes();
            if(!bytesReadResult.isSuccess()){
                throw new IllegalStateException("Failed to read resource file");
            }

            outputStream.write(bytesReadResult.getResult());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
