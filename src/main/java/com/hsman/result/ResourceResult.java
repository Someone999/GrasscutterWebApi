package com.hsman.result;

import lombok.Getter;

import java.io.File;
import java.io.IOException;

public abstract class ResourceResult<T> extends Result<T>{
    public abstract boolean isCreated();

    public abstract boolean isExists();

    public abstract boolean create();

    public ResourceResult(T result) {
        super(result, true, null);
    }

    public ResourceResult(T result, Exception exception) {
        super(result, exception != null, exception);
    }
}


