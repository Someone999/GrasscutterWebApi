package com.hsman.result;

import lombok.Getter;

public class BooleanResult {
    @Getter
    Exception exception;

    @Getter
    boolean success;

    public BooleanResult(boolean success, Exception exception) {
        this.exception = exception;
        this.success = success;
    }
}
