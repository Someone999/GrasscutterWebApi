package com.hsman.utils;

import com.hsman.result.FileResourceResult;
import com.hsman.result.ResourceResult;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
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

    public static ResourceResult<File> createFileInPluginPath(String fileName, boolean throwWhenExisted) {
        var path = Paths.get(getPluginPath(), fileName).toString();
        File file = new File(path);
        FileResourceResult result = new FileResourceResult(file);
        if(result.isExists()) {
            if(throwWhenExisted) {
                throw new IllegalStateException("File existed");
            } else {
                return result;
            }
        }

        result.create();
        return result;
    }

    @NotNull
    public static ResourceResult<File> getFileInPluginPath(String fileName, boolean autoCreate) {
        var path = Paths.get(getPluginPath(), fileName).toString();
        File file = new File(path);
        FileResourceResult result = new FileResourceResult(file);
        if(result.isExists()) {
            return result;
        }

        if(autoCreate) {
            return createFileInPluginPath(fileName, false);
        }

        return result;
    }
}
