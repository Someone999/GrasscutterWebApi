package com.hsman.result;

import lombok.Getter;

public class ParseResult<T> {
    public ParseResult(T result, boolean success) {
        this.result = result;
        this.success = success;
    }

    @Getter
    final boolean success;

    @Getter
    final T result;

    public static <T> ParseResult<T> getFailedResult() {
        return new ParseResult<>(null, false);
    }
}

