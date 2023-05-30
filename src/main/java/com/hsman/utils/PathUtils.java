package com.hsman.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class PathUtils {
    public static String getWorkingDir() {
        return System.getProperty("user.dir");
    }

    public static String getPluginPath() {
        String workingDir = getWorkingDir();
        var dir = Paths.get(workingDir, "plugins", "HSMan's").toString();
        var gcPluginPath = new File(dir);
        var selfPath = new File(Paths.get(workingDir, "HSMan's").toString());
        if(!gcPluginPath.exists() && !selfPath.exists()) {
            var r = selfPath.mkdir();
            if(r) {
                return selfPath.getPath();
            }
            return workingDir;

        }

        if(gcPluginPath.exists()) {
            return gcPluginPath.getPath();
        }
        else {
            return selfPath.getPath();
        }
    }
}
