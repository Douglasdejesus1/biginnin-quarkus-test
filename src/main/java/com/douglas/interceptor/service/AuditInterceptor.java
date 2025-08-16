package com.douglas.interceptor.service;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Audited
@Interceptor
@Priority(Interceptor.Priority.APPLICATION) // garante que o interceptor será chamado
public class AuditInterceptor {

    @AroundInvoke
    public Object logAudit(InvocationContext ctx) throws Exception {
        String method = ctx.getMethod().getDeclaringClass().getSimpleName()
                + "." + ctx.getMethod().getName();

        System.out.println("📌 [AUDIT] Iniciando chamada de: " + method);

        try {
            Object result = ctx.proceed(); // executa o método original
            System.out.println("✅ [AUDIT] Método concluído: " + method);
            return result;
        } catch (Exception e) {
            System.out.println("❌ [AUDIT] Erro no método: " + method + " -> " + e.getMessage());
            throw e;
        }
    }
}
