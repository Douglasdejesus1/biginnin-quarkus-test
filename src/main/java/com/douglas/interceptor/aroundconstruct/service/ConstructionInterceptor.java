package com.douglas.interceptor.aroundconstruct.service;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundConstruct;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Monitored
@Interceptor
@Priority(2000)
public class ConstructionInterceptor {

    @AroundConstruct
    public void aroundConstruct(InvocationContext ctx) throws Exception {
        System.out.println("➡️ Antes da construção do bean: " + ctx.getConstructor());
        ctx.proceed(); // chama o construtor do bean
        System.out.println("⬅️ Depois da construção do bean");
    }
}