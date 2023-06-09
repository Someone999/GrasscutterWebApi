package com.hsman.utils;

import com.hsman.result.ResourceInfo;
import com.hsman.result.ResourceResult;
import com.hsman.result.Result;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class ResourceUtils {
    @NotNull
    public static Result<ResourceInfo> getResourceFile(String path) {

        ClassLoader classLoader = ResourceUtils.class.getClassLoader();
        var resource = classLoader.getResource(path);
        if(resource == null) {
            return new Result<>(null, false);
        }
        return new Result<>(new ResourceInfo(resource), true);
    }

    @NotNull
    public static ResourceInfo[] getResourceFiles(String path)  {
        ClassLoader classLoader = ResourceUtils.class.getClassLoader();
        try {
            ArrayList<ResourceInfo> arrayList = new ArrayList<>();
            var resources = classLoader.getResources(path);
            while(resources.hasMoreElements()) {
                arrayList.add(new ResourceInfo(resources.nextElement()));
            }

            return arrayList.toArray(ResourceInfo[]::new);

        } catch (IOException e) {
            return new ResourceInfo[0];
        }
    }
}
