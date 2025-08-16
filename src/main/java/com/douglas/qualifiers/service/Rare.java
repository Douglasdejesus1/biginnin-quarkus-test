package com.douglas.qualifiers.service;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier  //CDI precisa de um qualifier concreto, ou seja, uma anotação definida por você, para diferenciar beans
@Retention(RUNTIME) //Define por quanto tempo a anotação é mantida.
@Target({TYPE, METHOD, FIELD, PARAMETER}) // @Target({TYPE, METHOD, FIELD, PARAMETER}) -> define que esta anotação
// pode ser usada em classes, métodos, campos e parâmetros de injeção.
public @interface Rare {}
