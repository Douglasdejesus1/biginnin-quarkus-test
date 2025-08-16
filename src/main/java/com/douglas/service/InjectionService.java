package com.douglas.service;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.stream.Collectors;

@Dependent
@Rare
public class InjectionService {

    private final String nomeDoPontoDeInjecao;
    private final String classeDeclarando;
    private final String tipoDoMembro;
    private final String nomeDoMembro;
    private final String tipoDoPontoDeInjecao;
    private final String qualifiers;
    private final String annotations;

    @Inject
    public InjectionService(InjectionPoint ip) {
        this.nomeDoPontoDeInjecao = ip.getMember().getName();
        this.classeDeclarando = ip.getMember().getDeclaringClass().getSimpleName();
        this.tipoDoMembro = ip.getMember().getClass().getSimpleName();
        this.nomeDoMembro = ip.getMember().toString();
        this.tipoDoPontoDeInjecao = ip.getType().getTypeName();

        this.qualifiers = ip.getQualifiers().stream()
                .map(a -> a.annotationType().getSimpleName())
                .collect(Collectors.joining(", "));

        this.annotations = ip.getAnnotated().getAnnotations().stream()
                .map(Annotation::annotationType)
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "));
    }

    public String dizerDeOndeFoiInjetado() {
        return String.format(
                "InjectionService foi injetado em: %s%n" +
                        "Classe declarando: %s%n" +
                        "Tipo do membro: %s%n" +
                        "Nome do membro: %s%n" +
                        "Tipo do ponto de injeção: %s%n" +
                        "Qualifiers: %s%n" +
                        "Annotations no membro: %s",
                nomeDoPontoDeInjecao,
                classeDeclarando,
                tipoDoMembro,
                nomeDoMembro,
                tipoDoPontoDeInjecao,
                qualifiers.isEmpty() ? "Nenhum" : qualifiers,
                annotations.isEmpty() ? "Nenhuma" : annotations
        );
    }
}
