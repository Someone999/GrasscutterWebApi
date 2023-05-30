package com.hsman.web.annotations;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    String name();
}
