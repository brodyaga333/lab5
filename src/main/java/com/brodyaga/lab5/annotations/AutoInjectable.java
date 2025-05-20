package com.brodyaga.lab5.annotations;

import java.lang.annotation.*;

/**
 * Аннотация для автоматического внедрения зависимостей.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}

