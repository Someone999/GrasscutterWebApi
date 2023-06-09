package com.hsman.result;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceInfo {
    URL resourceUrl;
    public ResourceInfo(@NotNull URL resUrl) {
        resourceUrl = resUrl;
    }

    public Result<InputStream> getResourceStream() {
        try {
            var stream = resourceUrl.openStream();
            return new Result<>(stream, stream != null);
        } catch (IOException e) {
            return new Result<>(null, false, e);
        }
    }

    public URL getResourceUrl() {
        return resourceUrl;
    }

    public Result<byte[]> readAllBytes() {
        var stream = getResourceStream();
        if(stream.isSuccess()) {
            try {
                return new Result<>(stream.getResult().readAllBytes(), true);
            } catch (IOException e) {
                return new Result<>(null, false, e);
            }
        }

        return new Result<>(null, false, stream.getException());
    }

}
