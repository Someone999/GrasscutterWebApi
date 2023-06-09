package com.hsman.result;

import lombok.Getter;

public class Result<T> extends BooleanResult {

    @Getter
    T result;

    public Result(T result, boolean success) {
        super(success, null);
        this.result = result;

    }

    public Result(T result, boolean success, Exception exception) {
        super(success, exception);
        this.result = result;

    }
}
