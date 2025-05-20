package com.brodyaga.lab5;


import com.brodyaga.lab5.di.Injector;


/**
 * Точка входа для демонстрации работы автоматического внедрения зависимостей.
 */
public class Main {
    public static void main(String[] args) {
        SomeBean bean = new Injector().inject(new SomeBean());
        bean.foo(); // AC или BC — в зависимости от config.properties
    }
}
