package com.hsman.utils;

import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class ResourceUtils {
    @Nullable
    public static InputStream getResourceFile(String path) {

        ClassLoader classLoader = ResourceUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(path);
    }

    @Nullable
    public static Enumeration<URL> getResourceFiles(String path)  {
        ClassLoader classLoader = ResourceUtils.class.getClassLoader();
        try {
            return classLoader.getResources(path);
        } catch (IOException e) {
            return null;
        }
    }
}
