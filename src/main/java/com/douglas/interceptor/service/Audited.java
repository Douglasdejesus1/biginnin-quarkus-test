package com.douglas.interceptor.service;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited // se aplicada numa classe base, as subclasses herdam
@InterceptorBinding // marca que pode ativar interceptores CDI
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Audited {
}