package com.hsman.web.middlewares;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class MiddlewareResult {
    @Getter @Setter @Builder.Default
    public boolean abort = false;
}
